
package com.acrolinx.sidebar;

@SuppressWarnings("unused")
public class LogSettings
{
    /**
     * Set the path for current logfile.
     * @param filePath
     * @param fileName
     */
    public static void setLogFilePath(String filePath, String fileName)
    {
        System.setProperty("log.name", fileName);
        System.setProperty("USER_HOME", filePath);
    }

}
