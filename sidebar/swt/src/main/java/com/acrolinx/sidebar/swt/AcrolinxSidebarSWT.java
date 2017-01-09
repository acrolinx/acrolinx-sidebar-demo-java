package com.acrolinx.sidebar.swt;

import com.acrolinx.sidebar.AcrolinxIntegration;
import com.acrolinx.sidebar.AcrolinxSidebar;
import com.acrolinx.sidebar.pojo.SidebarError;
import com.acrolinx.sidebar.pojo.document.*;
import com.acrolinx.sidebar.pojo.settings.CheckOptions;
import com.acrolinx.sidebar.pojo.settings.SidebarConfiguration;
import com.acrolinx.sidebar.utils.Lookup;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.math.IntRange;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class AcrolinxSidebarSWT implements AcrolinxSidebar
{
    private final Logger logger = LoggerFactory.getLogger(AcrolinxSidebarSWT.class);

    private final Browser browser;
    private final int prefHeight;
    private final AcrolinxIntegration client;
    private AtomicReference<String> lastCheckedText = new AtomicReference<>("");
    private AtomicReference<String> currentCheckId = new AtomicReference<>("");

    public AcrolinxSidebarSWT(Composite parent, int prefHeight, AcrolinxIntegration client)
    {
        this.client = client;
        this.browser = new Browser(parent, SWT.BORDER);
        this.prefHeight = prefHeight;
        initBrowser();
    }

    public AcrolinxSidebarSWT(Shell parent, int prefHeight, AcrolinxIntegration client)
    {
        this.client = client;
        this.browser = new Browser(parent, SWT.BORDER);
        this.prefHeight = prefHeight;
        initBrowser();
    }

    private void initBrowser()
    {
        this.browser.setSize(300, prefHeight);
        browser.setUrl(client.getInitParameters().getSidebarUrl());
        browser.addProgressListener(new ProgressListener()
        {
            @Override
            public void completed(ProgressEvent event)
            {
                logger.debug("URL loaded");
                initSidebar();
            }

            @Override
            public void changed(ProgressEvent event)
            {
            }
        });
    }

    private void initSidebar()
    {
        new BrowserFunction(browser, "overwriteJSLoggingP")
        {
            @Override
            public Object function(final Object[] arguments)
            {
                logger.debug("JSLogging: " + arguments[0]);
                return null;
            }
        };

        new BrowserFunction(browser, "getInitParamsP")
        {
            @Override
            public Object function(final Object[] arguments)
            {
                return client.getInitParameters().toString();
            }
        };

        new BrowserFunction(browser, "getTextP")
        {
            @Override
            public Object function(final Object[] arguments)
            {
                final String requestText = client.getEditorAdapter().getContent();
                lastCheckedText.set(requestText);
                if (Strings.isNullOrEmpty(requestText)) {
                    return "<unsupported/>";
                }
                logger.debug("REQUESTTEXT: " + requestText);
                return requestText;
            }

        };

        new BrowserFunction(browser, "onInitFinishedNotificationP")
        {
            @Override
            public Object function(final Object[] arguments)
            {
                String result = arguments[0].toString();
                JsonParser parser = new JsonParser();
                JsonObject json = (JsonObject) parser.parse(result);
                logger.debug("Init Finished!");
                logger.debug("Init result: " + result);
                JsonObject error = json.getAsJsonObject("error");
                if (error != null) {
                    SidebarError sidebarError = new Gson().fromJson(error, SidebarError.class);
                    client.onInitFinished(Optional.of(sidebarError));
                } else {
                    client.onInitFinished(Optional.empty());
                }
                return null;
            }
        };

        new BrowserFunction(browser, "getInputFormatP")
        {
            @Override
            public Object function(final Object[] arguments)
            {
                final String inputFormat = client.getEditorAdapter().getInputFormat().toString();
                logger.debug("INPUTFORMAT: " + inputFormat);
                return inputFormat;
            }
        };

        new BrowserFunction(browser, "onCheckResultP")
        {
            @Override
            public Object function(final Object[] arguments)
            {
                String checkResult = arguments[0].toString();
                logger.debug("CheckResult: " + checkResult);
                try {
                    CheckResultFromJSON checkResultObj = new Gson().fromJson(checkResult, CheckResultFromJSON.class);
                    CheckResult result = checkResultObj.getAsCheckResult();
                    client.onCheckResult(result);
                    currentCheckId.set(result.getCheckedDocumentPart().getCheckId());
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                return null;
            }
        };

        new BrowserFunction(browser, "selectRangesP")
        {
            @Override
            public Object function(final Object[] arguments)
            {
                logger.debug(arguments[0] + " " + arguments[1]);
                try {
                    List<AcrolinxMatchFromJSON> match = new Gson().fromJson((String) arguments[1],
                            new TypeToken<List<AcrolinxMatchFromJSON>>() {}.getType());
                    List<AcrolinxMatch> result = match.stream().map(AcrolinxMatchFromJSON::getAsAcrolinxMatch).collect(
                            Collectors.toCollection(ArrayList::new));
                    Optional<IntRange> range = Lookup.getCorrectedRangeFromAcrolinxMatch(result, lastCheckedText.get(),
                            client.getEditorAdapter().getContent());
                    client.getEditorAdapter().selectRanges((String) arguments[0], result, range);
                    if (!range.isPresent()) {
                        invalidateRanges(result.stream().map(
                                acrolinxMatch -> new CheckedDocumentPart(currentCheckId.get(),
                                        new IntRange(acrolinxMatch.getRange().getMinimumInteger(),
                                                acrolinxMatch.getRange().getMaximumInteger()))).collect(
                                Collectors.toList()));
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                return null;
            }

        };
        new BrowserFunction(browser, "replaceRangesP")
        {
            @Override
            public Object function(final Object[] arguments)
            {
                logger.debug(arguments[0] + " " + arguments[1]);
                try {
                    List<AcrolinxMatchFromJSON> match = new Gson().fromJson((String) arguments[1],
                            new TypeToken<List<AcrolinxMatchFromJSON>>() {}.getType());
                    List<AcrolinxMatchWithReplacement> result = match.stream().map(
                            AcrolinxMatchFromJSON::getAsAcrolinxMatchWithReplacement).collect(
                            Collectors.toCollection(ArrayList::new));
                    Optional<IntRange> range = Lookup.getCorrectedRangeFromAcrolinxMatchWithReplacement(result,
                            lastCheckedText.get(), client.getEditorAdapter().getContent());
                    client.getEditorAdapter().replaceRanges((String) arguments[0], result, range);
                    if (!range.isPresent()) {
                        invalidateRanges(result.stream().map(
                                acrolinxMatchWithReplacement -> new CheckedDocumentPart(currentCheckId.get(),
                                        new IntRange(acrolinxMatchWithReplacement.getRange().getMinimumInteger(),
                                                acrolinxMatchWithReplacement.getRange().getMaximumInteger()))).collect(
                                Collectors.toList()));
                    }
                } catch (Exception e) {
                    logger.debug(e.getMessage());
                }
                return null;
            }

        };
        new BrowserFunction(browser, "getDocUrlP")
        {
            @Override
            public Object function(final Object[] arguments)
            {
                return client.getEditorAdapter().getDocumentReference();
            }

        };

        new BrowserFunction(browser, "notifyAboutSidebarConfigurationP")
        {
            @Override
            public Object function(final Object[] arguments)
            {
                return null;
            }
        };

        new BrowserFunction(browser, "downloadP")
        {
            @Override
            public Object function(final Object[] arguments)
            {
                return null;
            }
        };

        new BrowserFunction(browser, "openWindowP")
        {
            @Override
            public Object function(final Object[] arguments)
            {
                String result = arguments[0].toString();
                String url = result.substring(result.indexOf("http"), result.length() - 2);
                client.openWindow(url);
                return null;
            }
        };

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource("acrolinxPluginScript.js");
            if (resource != null) {
                File jsScript = new File(resource.getFile());
                BufferedReader reader = new BufferedReader(new FileReader(jsScript));
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                String script = sb.toString();
                reader.close();
                browser.evaluate(script);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public Browser getSidebarBrowser()
    {
        return this.browser;
    }

    @Override
    public void configure(SidebarConfiguration configuration)
    {
        browser.execute("window.acrolinxSidebar.configure(" + configuration.toString() + ");");
    }

    @Override
    public CompletableFuture<String> checkGlobal(String content, CheckOptions options)
    {
        CompletableFuture<String> future = new CompletableFuture<>();
        String result = (String) browser.evaluate(
                "JSON.stringify(window.acrolinxSidebar.checkGlobal(" + content + "," + options.toString() + "));");
        future.complete(result);
        return future;
    }

    @Override
    public void onGlobalCheckRejected()
    {
        browser.execute("window.acrolinxSidebar.onGlobalCheckRejected();");
    }

    private static String buildStringOfCheckedDocumentRanges(java.util.List<CheckedDocumentPart> checkedDocumentParts)
    {
        return checkedDocumentParts.stream().map(checkedDocumentPart -> checkedDocumentPart.getAsJS()).collect(
                Collectors.joining(", "));
    }

    @Override
    public void invalidateRanges(List<CheckedDocumentPart> invalidDocumentParts)
    {
        String js = buildStringOfCheckedDocumentRanges(invalidDocumentParts);
        browser.execute("window.acrolinxSidebar.invalidateRanges([" + js + "]);");
    }
}
