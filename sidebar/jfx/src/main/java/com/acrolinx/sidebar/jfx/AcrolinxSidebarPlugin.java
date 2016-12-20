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
import com.acrolinx.sidebar.pojo.document.CheckResult;
import com.acrolinx.sidebar.pojo.document.CheckedDocumentPart;
import com.acrolinx.sidebar.pojo.settings.*;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import javafx.application.Platform;
import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

public class AcrolinxSidebarPlugin
{
    private final AcrolinxIntegration client;
    private final JSObject jsobj;
    private final AtomicReference<String> lastCheckedDocument = new AtomicReference<>("");
    private final AtomicReference<InputFormat> inputFormatRef = new AtomicReference<>();
    private final AtomicReference<AcrolinxSidebarInitParameter> initParameters = new AtomicReference<>();

    final Logger logger = LoggerFactory.getLogger(AcrolinxSidebarPlugin.class);

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

    public synchronized void configure(final JSObject o)
    {
        invokeSave(() -> {
            final AcrolinxPluginConfiguration pluginConfiguration = JSToJavaConverter.getAcrolinxPluginConfigurationFromJSObject(
                    o);
            client.configure(pluginConfiguration);
        });
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
        invokeSave(() -> client.onCheckResult(checkResult));
    }

    public synchronized void selectRanges(final String checkID, final JSObject o)
    {
        final List<AcrolinxMatch> matches = JSToJavaConverter.getAcrolinxMatchFromJSObject(o);
        invokeSave(() -> client.getEditorAdapter().selectRanges(checkID,
                JSToJavaConverter.getAcrolinxMatchFromJSObject(o)));
    }

    public synchronized void replaceRanges(final String checkID, final JSObject o)
    {
        // TODO (fp) lookup and adjust ranges
        invokeSave(() -> client.getEditorAdapter().replaceRanges(checkID,
                JSToJavaConverter.getAcrolinxMatchWithReplacementFromJSObject(o)));
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
        // TODO (fp) filename in requestDescription
        return new CheckOptions(Optional.of(inputFormatRef.get()), Optional.of(false), Optional.empty());
    }

    protected void onGlobalCheckRejected()
    {
        Platform.runLater(() -> jsobj.eval("acrolinxSidebar.onGlobalCheckRejected()"));
    }

    protected void invalidateRanges(CheckedDocumentPart[] invalidCheckedDocumentRanges)
    {
        String json = new Gson().toJson(invalidCheckedDocumentRanges);
        Platform.runLater(() -> jsobj.eval("acrolinxSidebar.onGlobalCheckRejected(JSON.parse(" + json + "))"));
    }

    protected void onVisibleRangesChanged(CheckedDocumentPart[] invalidCheckedDocumentRanges)
    {
        String json = new Gson().toJson(invalidCheckedDocumentRanges);
        Platform.runLater(() -> jsobj.eval("acrolinxSidebar.onVisibleRangeChanged(JSON.parse(" + json + "))"));
    }
}
