package com.github.caijh.commons.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ProcessUtils {

    private ProcessUtils() {
    }

    public static String readConsoleString(Process process) throws IOException {
        StringBuilder console = new StringBuilder();
        BufferedReader bufferIn = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
        BufferedReader bufferedError = new BufferedReader(new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8));

        String line;
        while ((line = bufferIn.readLine()) != null || (line = bufferedError.readLine()) != null) {
            console.append(line).append('\n');
        }
        return console.toString();
    }

}
