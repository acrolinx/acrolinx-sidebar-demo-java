/**
 * (c) 2015 Acrolinx GmbH. All rights reserved.
 *
 * Created 19.05.2015
 *
 * @author ralf
 */

package com.acrolinx.sidebar.jfx;

import com.acrolinx.sidebar.AcrolinxIntegration;
import com.acrolinx.sidebar.pojo.InitResult;
import com.acrolinx.sidebar.pojo.document.AcrolinxMatch;
import com.acrolinx.sidebar.pojo.document.AcrolinxMatchWithReplacement;
import com.acrolinx.sidebar.pojo.document.CheckResult;
import com.acrolinx.sidebar.pojo.document.CheckedDocumentPart;
import com.acrolinx.sidebar.pojo.settings.*;
import com.acrolinx.sidebar.utils.Lookup;
import com.google.common.base.Preconditions;
import javafx.application.Platform;
import netscape.javascript.JSObject;
import org.apache.commons.lang.math.IntRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class AcrolinxSidebarPlugin
{
    private final AcrolinxIntegration client;
    private final JSObject jsobj;
    private final AtomicReference<String> lastCheckedDocument = new AtomicReference<>("");
    private final AtomicReference<String> currentCheckId = new AtomicReference<>("");
    private final AtomicReference<InputFormat> inputFormatRef = new AtomicReference<>();
    private final AtomicReference<String> documentReference = new AtomicReference<>("");
    private final AtomicReference<AcrolinxSidebarInitParameter> initParameters = new AtomicReference<>();

    private final Logger logger = LoggerFactory.getLogger(AcrolinxSidebarPlugin.class);

    public AcrolinxSidebarPlugin(final AcrolinxIntegration client, final JSObject jsobj)
    {
        Preconditions.checkNotNull(jsobj, "jsobj should not be null");
        Preconditions.checkNotNull(client, "workspace should not be null");

        this.client = client;
        this.jsobj = jsobj;
    }

    private AcrolinxIntegration getClient()
    {
        return client;
    }

    public synchronized void requestInit()
    {
        this.initParameters.set(client.getInitParameters());
        Platform.runLater(() -> jsobj.eval("acrolinxSidebar.init(" + this.initParameters.get().toString() + ")"));
    }

    public synchronized void onInitFinished(final JSObject o)
    {
        final InitResult initResult = JSToJavaConverter.getAcrolinxInitResultFromJSObject(o);
        invokeSave(() -> client.onInitFinished(initResult));
    }

    public synchronized void configureSidebar(SidebarConfiguration sidebarConfiguration)
    {
        Platform.runLater(() -> jsobj.eval("acrolinxSidebar.configure(" + sidebarConfiguration.toString() + ")"));
    }

    public synchronized void requestGlobalCheck()
    {
        lastCheckedDocument.set(client.getEditorAdapter().getContent());
        final CheckOptions checkOptions = getCheckSettingsFromClient();

        Platform.runLater(() -> {
            jsobj.setMember("checkText", lastCheckedDocument.get());
            jsobj.eval("acrolinxSidebar.checkGlobal(checkText," + checkOptions.toString() + ")");
        });
    }

    public synchronized CompletableFuture<String> checkGlobal(String documentContent, CheckOptions checkOptions)
    {
        final CompletableFuture<String> future = new CompletableFuture<>();
        Platform.runLater(() -> {
            jsobj.eval("var globalCheckResult = JSON.stringify(acrolinxSidebar.checkGlobal(" + documentContent + ","
                    + checkOptions.toString() + "))");
            future.complete((String) jsobj.getMember("globalCheckResult"));
        });
        return future;
    }

    public synchronized void onCheckResult(final JSObject o)
    {
        final CheckResult checkResult = JSToJavaConverter.getCheckResultFromJSObject(o);
        currentCheckId.set(checkResult.getCheckedDocumentPart().getCheckId());
        invokeSave(() -> client.onCheckResult(checkResult));
    }

    public synchronized void selectRanges(final String checkID, final JSObject o)
    {
        final List<AcrolinxMatch> matches = JSToJavaConverter.getAcrolinxMatchFromJSObject(o);
        final Optional<IntRange> correctedRanges = Lookup.getCorrectedRangeFromAcrolinxMatch(matches,
                lastCheckedDocument.get(), client.getEditorAdapter().getContent());
        if (!correctedRanges.isPresent()) {
            List<CheckedDocumentPart> invalidDocumentParts = matches.stream().map(
                    (match) -> new CheckedDocumentPart(currentCheckId.get(),
                            new IntRange(match.getRange().getMinimumNumber(),
                                    match.getRange().getMaximumInteger()))).collect(Collectors.toList());
            invalidateRanges(invalidDocumentParts);
            return;
        }
        invokeSave(() -> client.getEditorAdapter().selectRanges(checkID, matches, correctedRanges));

    }

    public synchronized void replaceRanges(final String checkID, final JSObject o)
    {
        final List<AcrolinxMatchWithReplacement> matches = JSToJavaConverter.getAcrolinxMatchWithReplacementFromJSObject(
                o);
        final Optional<IntRange> correctedRanges = Lookup.getCorrectedRangeFromAcrolinxMatchWithReplacement(matches,
                lastCheckedDocument.get(), client.getEditorAdapter().getContent());
        if (!correctedRanges.isPresent()) {
            List<CheckedDocumentPart> invalidDocumentParts = matches.stream().map(
                    (match) -> new CheckedDocumentPart(currentCheckId.get(),
                            new IntRange(match.getRange().getMinimumNumber(),
                                    match.getRange().getMaximumInteger()))).collect(Collectors.toList());
            invalidateRanges(invalidDocumentParts);
            return;
        }
        invokeSave(() -> client.getEditorAdapter().replaceRanges(checkID, matches, correctedRanges));
    }

    public synchronized void openWindow(final JSObject o)
    {
        invokeSave(() -> client.openWindow(o.getMember("url").toString()));
    }

    private void invokeSave(Runnable runnable)
    {
        this.getClient().getInvocationAdapter().invokeSave(runnable);
    }

    private CheckOptions getCheckSettingsFromClient()
    {
        inputFormatRef.set(client.getEditorAdapter().getInputFormat());
        documentReference.set(client.getEditorAdapter().getDocumentReference());
        return new CheckOptions(new RequestDescription(documentReference.get()), inputFormatRef.get());
    }

    protected void onGlobalCheckRejected()
    {
        Platform.runLater(() -> jsobj.eval("acrolinxSidebar.onGlobalCheckRejected()"));
    }

    private static String buildStringOfCheckedDocumentRanges(java.util.List<CheckedDocumentPart> checkedDocumentParts)
    {
        return checkedDocumentParts.stream().map(checkedDocumentPart -> checkedDocumentPart.getAsJS()).collect(
                Collectors.joining(", "));
    }

    protected void invalidateRanges(List<CheckedDocumentPart> invalidCheckedDocumentRanges)
    {
        String js = buildStringOfCheckedDocumentRanges(invalidCheckedDocumentRanges);
        logger.debug(js);
        Platform.runLater(() -> jsobj.eval("acrolinxSidebar.invalidateRanges([" + js + "])"));
    }
}
