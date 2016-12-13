/**
 * (c) 2015 Acrolinx GmbH. All rights reserved.
 *
 * Created 19.05.2015
 *
 * @author ralf
 */

package com.acrolinx.sidebar;

import com.acrolinx.sidebar.document.AcrolinxMatch;
import com.acrolinx.sidebar.document.CheckResult;
import com.acrolinx.sidebar.settings.*;
import com.google.common.base.Preconditions;
import javafx.application.Platform;
import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class AcrolinxSidebarPlugin
{
    private AcrolinxIntegration client;
    private JSObject jsobj;
    private AtomicReference<String> lastCheckedDocument = new AtomicReference<>("");
    private AtomicReference<InputFormat> inputFormatRef = new AtomicReference<>();
    private AtomicReference<AcrolinxSidebarInitParemeters> initParameters = new AtomicReference<>();

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

    public void requestInit()
    {
        this.initParameters.set(client.getInitParameters());
        Platform.runLater(() -> jsobj.eval("acrolinxSidebar.init(" + this.initParameters.get().toJSString() + ")"));
    }

    public void onInitFinished(final JSObject o)
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

    public synchronized void onCheckResult(final JSObject o)
    {
        final CheckResult checkResult = JSToJavaConverter.getCheckResultFromJSObject(o);
        invokeSave(() -> client.onCheckResult(checkResult));
    }

    public void selectRanges(final String checkID, final JSObject o)
    {
        final List<AcrolinxMatch> matches = JSToJavaConverter.getAcrolinxMatchFromJSObject(o);
        invokeSave(() -> client.getEditorAdapter().selectRanges(checkID,
                JSToJavaConverter.getAcrolinxMatchFromJSObject(o)));
    }

    public void replaceRanges(final String checkID, final JSObject o)
    {
        // TODO (fp) lookup and adjust ranges
        invokeSave(() -> client.getEditorAdapter().replaceRanges(checkID,
                JSToJavaConverter.getAcrolinxMatchWithReplacementFromJSObject(o)));
    }

    public void openWindow(final JSObject o)
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
}
