package com.acrolinx.sidebar.jfx;

import com.acrolinx.sidebar.pojo.InitResult;
import com.acrolinx.sidebar.pojo.SidebarError;
import com.acrolinx.sidebar.pojo.document.*;
import com.acrolinx.sidebar.pojo.settings.AcrolinxPluginConfiguration;
import com.google.common.collect.Lists;
import netscape.javascript.JSObject;
import org.apache.commons.lang.math.IntRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

class JSToJavaConverter
{
    final Logger logger = LoggerFactory.getLogger(JSToJavaConverter.class);

    static List<AcrolinxMatch> getAcrolinxMatchFromJSObject(JSObject o)
    {
        final String length = "" + o.getMember("length");
        final List<AcrolinxMatch> acrolinxMatches = Lists.newArrayList();
        for (int i = 0; i < Integer.parseInt(length); i++) {
            final IntRange range = getIntRangeFromJSString(((JSObject) o.getSlot(i)).getMember("range").toString());
            final String surface = "" + ((JSObject) o.getSlot(i)).getMember("content");
            acrolinxMatches.add(new AcrolinxMatch(range, surface));
        }
        return Collections.unmodifiableList(acrolinxMatches);
    }

    static List<AcrolinxMatchWithReplacement> getAcrolinxMatchWithReplacementFromJSObject(JSObject o)
    {
        final String length = "" + o.getMember("length");
        final List<AcrolinxMatchWithReplacement> acrolinxMatches = Lists.newArrayList();
        for (int i = 0; i < Integer.parseInt(length); i++) {
            final IntRange range = getIntRangeFromJSString(((JSObject) o.getSlot(i)).getMember("range").toString());
            final String surface = "" + ((JSObject) o.getSlot(i)).getMember("content");
            final String replacement = "" + ((JSObject) o.getSlot(i)).getMember("replacement");
            acrolinxMatches.add(new AcrolinxMatchWithReplacement(surface, range, replacement));
        }
        return Collections.unmodifiableList(acrolinxMatches);
    }

    private static IntRange getIntRangeFromJSString(String range)
    {
        String[] parts = range.split(",");
        IntRange intRange;
        if (parts.length == 2) {
            intRange = new IntRange(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        } else
            intRange = null;
        return intRange;
    }

    static CheckResult getCheckResultFromJSObject(JSObject o)
    {
        final JSObject checkedDocumentParts = (JSObject) o.getMember("checkedPart");
        final String checkId = checkedDocumentParts.getMember("checkId").toString();
        final IntRange range = getIntRangeFromJSString(checkedDocumentParts.getMember("range").toString());
        CheckError checkError = null;
        if (!o.getMember("checkError").toString().equals("undefined")) {
            checkError = getCheckErrorFromJSString((JSObject) o.getMember("checkError"));
        }
        return new CheckResult(new CheckedDocumentPart(checkId, range), checkError);
    }

    private static CheckError getCheckErrorFromJSString(JSObject checkError)
    {
        if (checkError.getClass().equals(String.class)) {
            final String message = checkError.getMember("message").toString();
            final String code = checkError.getMember("code").toString();
            final String checkId = checkError.getMember("checkId").toString();
            return new CheckError(message, code, checkId);
        } else
            return null;
    }

    static AcrolinxPluginConfiguration getAcrolinxPluginConfigurationFromJSObject(JSObject o)
    {
        final JSObject pluginConf = (JSObject) o.getMember("supported");
        if (pluginConf != null) {
            final boolean isBase64EncodedGzippedDocumentContent = (Boolean) pluginConf.getMember(
                    "base64EncodedGzippedDocumentContent");
            return new AcrolinxPluginConfiguration(isBase64EncodedGzippedDocumentContent);
        }
        return new AcrolinxPluginConfiguration(false);
    }

    static InitResult getAcrolinxInitResultFromJSObject(JSObject o)
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




