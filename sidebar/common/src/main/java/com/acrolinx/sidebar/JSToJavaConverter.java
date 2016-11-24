package com.acrolinx.sidebar;

import com.acrolinx.sidebar.document.AcrolinxMatch;
import com.acrolinx.sidebar.document.AcrolinxMatchWithReplacement;
import com.acrolinx.sidebar.document.CheckResult;
import com.acrolinx.sidebar.document.CheckedDocumentPart;
import com.acrolinx.sidebar.settings.AcrolinxPluginConfiguration;
import com.google.common.collect.Lists;
import netscape.javascript.JSObject;
import org.apache.commons.lang.math.IntRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class JSToJavaConverter
{
    final Logger logger = LoggerFactory.getLogger(JSToJavaConverter.class);

    protected static List<AcrolinxMatch> getAcrolinxMatchFromJSObject(JSObject o)
    {
        final String length = "" + o.getMember("length");
        final List<AcrolinxMatch> acrolinxMatches = Lists.newArrayList();
        for (int i = 0; i < Integer.parseInt(length); i++) {
            final IntRange range = getIntRangeFromJSString(((JSObject) o.getSlot(i)).getMember("range").toString());
            final String surface = "" + ((JSObject) o.getSlot(i)).getMember("content");
            // TODO (fp) extracted Range!!!
            acrolinxMatches.add(new AcrolinxMatch(range, surface));
        }
        List<AcrolinxMatch> immutableList = Collections.unmodifiableList(acrolinxMatches);
        return immutableList;
    }

    protected static List<AcrolinxMatchWithReplacement> getAcrolinxMatchWithReplacementFromJSObject(JSObject o)
    {
        final String length = "" + o.getMember("length");
        final List<AcrolinxMatchWithReplacement> acrolinxMatches = Lists.newArrayList();
        for (int i = 0; i < Integer.parseInt(length); i++) {
            final IntRange range = getIntRangeFromJSString(((JSObject) o.getSlot(i)).getMember("range").toString());
            final String surface = "" + ((JSObject) o.getSlot(i)).getMember("content");
            final String replacement = "" + ((JSObject) o.getSlot(i)).getMember("replacement");
            // TODO (fp) extracted Range!!!
            acrolinxMatches.add(new AcrolinxMatchWithReplacement(surface, range, replacement));
        }
        List<AcrolinxMatchWithReplacement> immutableList = Collections.unmodifiableList(acrolinxMatches);
        return immutableList;
    }

    protected static IntRange getIntRangeFromJSString(String range)
    {
        String[] parts = range.split(",");
        IntRange intRange;
        if (parts.length == 2) {
            intRange = new IntRange(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        } else
            intRange = null;
        return intRange;
    }

    protected static CheckResult getCheckResultFromJSObject(JSObject o)
    {
        final JSObject checkedDocumentParts = (JSObject) o.getMember("checkedPart");
        final String checkId = checkedDocumentParts.getMember("checkId").toString();
        final IntRange range = getIntRangeFromJSString(checkedDocumentParts.getMember("range").toString());
        // TODO (fp) extract ErrorObject!
        return new CheckResult(new CheckedDocumentPart(checkId, range));
    }

    protected static AcrolinxPluginConfiguration getAcrolinxPluginConfigurationFromJSObject(JSObject o)
    {
        final JSObject pluginConf = (JSObject) o.getMember("supported");
        if (pluginConf != null) {
            final JSObject pluginConfObj = (JSObject) pluginConf;
            final boolean isBase64EncodedGzippedDocumentContent = (Boolean) pluginConfObj.getMember(
                    "base64EncodedGzippedDocumentContent");
            return new AcrolinxPluginConfiguration(isBase64EncodedGzippedDocumentContent);
        }
        return new AcrolinxPluginConfiguration(false);
    }

    protected static InitResult getAcrolinxInitResultFromJSObject(JSObject o)
    {
        final Object hasError = o.getMember("error");
        if (!hasError.getClass().equals(String.class)) {
            final JSObject error = (JSObject) hasError;
            final String code = error.getMember("code").toString();
            final String message = error.getMember("message").toString();
            return new InitResult(Optional.of(new SidebarError(message, code)));
        } else
            return new InitResult(Optional.empty());
    }
}




