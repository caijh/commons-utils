package com.github.caijh.commons.util.process;


/**
 * 调用命令的执行结果.
 */
public class ProcessResult {

    private int exitValue = -1;

    private String consoleString;

    public boolean isSuccessful() {
        return exitValue == 0;
    }

    public int getExitValue() {
        return exitValue;
    }

    public void setExitValue(int exitValue) {
        this.exitValue = exitValue;
    }

    public String getConsoleString() {
        return consoleString;
    }

    public void setConsoleString(String consoleString) {
        this.consoleString = consoleString;
    }

}
