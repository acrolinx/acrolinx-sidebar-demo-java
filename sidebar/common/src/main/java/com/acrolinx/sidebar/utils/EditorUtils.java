
package com.acrolinx.sidebar.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.acrolinx.sidebar.pojo.settings.InputFormat;

public class EditorUtils
{
    public static InputFormat getInputFormat(String fileName)
    {
        final String fileExtensionPattern = "(\\.(?i)(txt|xml|dita|html|md))";
        Pattern fileExtensionExtracter = Pattern.compile(fileExtensionPattern);
        Matcher matcher = fileExtensionExtracter.matcher(fileName);
        InputFormat inputFormat;
        if (matcher.find()) {
            switch (matcher.group().toLowerCase()) {
                case ".txt":
                    inputFormat = InputFormat.TEXT;
                    break;
                case ".xml":
                    inputFormat = InputFormat.XML;
                    break;
                case ".dita":
                    inputFormat = InputFormat.XML;
                    break;
                case ".html":
                    inputFormat = InputFormat.HTML;
                    break;
                case ".md":
                    inputFormat = InputFormat.MARKDOWN;
                    break;
                default:
                    inputFormat = InputFormat.TEXT;
                    break;
            }
        } else {
            inputFormat = null;
        }
        return inputFormat;
    }

}
