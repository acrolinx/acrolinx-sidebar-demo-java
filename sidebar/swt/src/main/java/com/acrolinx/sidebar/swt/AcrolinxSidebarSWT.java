package com.acrolinx.sidebar.swt;

import com.acrolinx.sidebar.AcrolinxIntegration;
import com.acrolinx.sidebar.AcrolinxSidebar;
import com.acrolinx.sidebar.pojo.InitResult;
import com.acrolinx.sidebar.pojo.document.*;
import com.acrolinx.sidebar.pojo.settings.AcrolinxPluginConfiguration;
import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParemeters;
import com.acrolinx.sidebar.pojo.settings.CheckOptions;
import com.acrolinx.sidebar.pojo.settings.SidebarConfiguration;
import com.acrolinx.sidebar.utils.SidebarUtils;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AcrolinxSidebarSWT implements AcrolinxSidebar
{
    private final Logger logger = LoggerFactory.getLogger(AcrolinxSidebarSWT.class);

    Browser browser;
    int prefHeight;
    final AcrolinxIntegration client;

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
        browser.setUrl(AcrolinxSidebarInitParemeters.SIDEBAR_URL);
        browser.addProgressListener(new ProgressListener()
        {
            @Override public void completed(ProgressEvent event)
            {
                logger.debug("URL loaded");
                // Execute JavaScript in the browser
                initSidebar();
            }

            @Override public void changed(ProgressEvent event)
            {
            }
        });
    }

    private void initSidebar()
    {
        new BrowserFunction(browser, "overwriteJSLoggingP")
        {
            @Override public Object function(final Object[] arguments)
            {
                logger.debug("JSLogging: " + arguments[0]);
                return null;
            }
        };

        new BrowserFunction(browser, "getInitParamsP")
        {
            @Override public Object function(final Object[] arguments)
            {
                return client.getInitParameters().toString();
            }
        };

        new BrowserFunction(browser, "getTextP")
        {
            @Override public Object function(final Object[] arguments)
            {
                final String requestText = client.getEditorAdapter().getContent();
                if (Strings.isNullOrEmpty(requestText)) {
                    return "<unsupported/>";
                }
                logger.debug("REQUESTTEXT: " + requestText);
                return requestText;
            }

        };

        new BrowserFunction(browser, "onInitFinishedNotificationP")
        {
            @Override public Object function(final Object[] arguments)
            {
                String result = arguments[0].toString();
                logger.debug("Init Finished!");
                logger.debug("Init result: " + result);
                InitResult initResult = new Gson().fromJson(result, InitResult.class);
                client.onInitFinished(initResult);
                return null;
            }
        };

        new BrowserFunction(browser, "getInputFormatP")
        {
            @Override public Object function(final Object[] arguments)
            {
                final String inputFormat = client.getEditorAdapter().getInputFormat().toString();
                logger.debug("INPUTFORMAT: " + inputFormat);
                return inputFormat;
            }
        };

        new BrowserFunction(browser, "onCheckResultP")
        {
            @Override public Object function(final Object[] arguments)
            {
                String checkResult = arguments[0].toString();
                logger.debug("CheckResult: " + checkResult);
                try {
                    CheckResult checkResultObj = new Gson().fromJson(checkResult, CheckResult.class);
                    client.onCheckResult(checkResultObj);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                return null;
            }
        };

        new BrowserFunction(browser, "selectRangesP")
        {
            @Override public Object function(final Object[] arguments)
            {
                logger.debug(arguments[0] + " " + arguments[1]);
                try {
                    List<AcrolinxMatchFromJSON> match = new Gson().fromJson((String) arguments[1],
                            new TypeToken<List<AcrolinxMatchFromJSON>>() {}.getType());
                    List<AcrolinxMatch> result = match.stream().map(m -> m.getAsAcrolinxMatch()).collect(
                            Collectors.toCollection(ArrayList::new));
                    client.getEditorAdapter().selectRanges((String) arguments[0], result);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                return null;
            }

        };
        new BrowserFunction(browser, "replaceRangesP")
        {
            @Override public Object function(final Object[] arguments)
            {
                logger.debug(arguments[0] + " " + arguments[1]);
                try {
                    List<AcrolinxMatchFromJSON> match = new Gson().fromJson((String) arguments[1],
                            new TypeToken<List<AcrolinxMatchFromJSON>>() {}.getType());
                    List<AcrolinxMatchWithReplacement> result = match.stream().map(
                            m -> m.getAsAcrolinxMatchWithReplacement()).collect(
                            Collectors.toCollection(ArrayList::new));
                    client.getEditorAdapter().replaceRanges((String) arguments[0], result);
                } catch (Exception e) {
                    logger.debug(e.getMessage());
                }
                return null;
            }

        };
        new BrowserFunction(browser, "getDocUrlP")
        {
            @Override public Object function(final Object[] arguments)
            {
                return null;
            }

        };

        new BrowserFunction(browser, "notifyAboutSidebarConfigurationP")
        {
            @Override public Object function(final Object[] arguments)
            {
                String configuration = arguments[0].toString();
                logger.debug(configuration);
                try {
                    AcrolinxPluginConfiguration pluginConfiguration = new Gson().fromJson(configuration,
                            AcrolinxPluginConfiguration.class);
                    client.configure(pluginConfiguration);
                } catch (Exception e) {
                    logger.debug(e.getMessage());
                }
                return null;
            }
        };

        new BrowserFunction(browser, "downloadP")
        {
            @Override public Object function(final Object[] arguments)
            {
                logger.debug((String) arguments[0]);
                DownloadInfo downloadInfo = new Gson().fromJson((String) arguments[0], DownloadInfo.class);
                client.download(downloadInfo);
                return null;
            }
        };

        new BrowserFunction(browser, "openWindowP")
        {
            @Override public Object function(final Object[] arguments)
            {
                String result = arguments[0].toString();
                String url = result.substring(result.indexOf("http"), result.length() - 2);
                client.openWindow(url);
                return null;
            }
        };

        ClassLoader classLoader = getClass().getClassLoader();
        File jsScript = new File(classLoader.getResource("acrolinxPluginScript.js").getFile());
        try {
            BufferedReader reader = new BufferedReader(new FileReader(jsScript));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            String script = sb.toString();
            reader.close();
            browser.evaluate(script);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Browser getSidebarBrowser()
    {
        return this.browser;
    }

    @Override public void configure(SidebarConfiguration configuration)
    {
        browser.execute("window.acrolinxSidebar.configure(" + configuration.toString() + ");");
    }

    @Override public String checkGlobal(String content, CheckOptions options)
    {
        Object result = browser.evaluate(
                "window.acrolinxSidebar.checkGlobal(" + content + "," + options.toString() + ");");
        String checkId = result.toString().replace("{", "").replace("}", "");
        return checkId;
    }

    @Override public void onGlobalCheckRejected()
    {
        browser.execute("window.acrolinxSidebar.onGlobalCheckRejected();");
    }

    @Override public void invalidateRanges(CheckedDocumentPart[] invalidDocumentParts)
    {
        browser.execute("window.acrolinxSidebar.invalidateRanges(" +
                SidebarUtils.getCheckedDocumentPartsAsString(invalidDocumentParts) + ");");
    }

    @Override public void onVisibleRangesChanged(CheckedDocumentPart[] changedDocumentRanges)
    {
        browser.execute("window.acrolinxSidebar.onVisibleRangeChanged(" +
                SidebarUtils.getCheckedDocumentPartsAsString(changedDocumentRanges) + ")");
    }

}
