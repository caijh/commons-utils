package com.github.caijh.commons.util.process;

public interface Command {

    String[] getCmdArray();

    default ProcessResult exec() {
        ProcessResult result = new ProcessResult();
        try {
            String[] cmdArray = getCmdArray();
            Process process = Runtime.getRuntime().exec(cmdArray);
            process.waitFor();
            int exitValue = process.exitValue();
            String consoleString = ProcessUtils.readConsoleString(process);
            result.setExitValue(exitValue);
            result.setConsoleString(consoleString);
        } catch (Exception e) {
            throw new ProcessException("call process fail", e);
        }
        return result;
    }

}
