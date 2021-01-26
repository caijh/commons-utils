package com.github.caijh.commons.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Url {

    public static final Pattern PATTERN_URL = Pattern
        .compile("((?<schema>\\w+)://)?((?<user>\\w+):(?<password>\\w+)@)?(?<host>[^/:]+)(:(?<port>\\d*))?(?<path>[^ ]*)");
    private String schema;
    private String user;
    private String password;
    private String host;
    private String port;
    private String path;
    private Map<String, String> params;

    private Url() {

    }

    public static Url from(String urlString) {
        Matcher matcher = PATTERN_URL.matcher(urlString);
        Url url = null;
        if (matcher.matches()) {
            url = new Url();
            url.schema = matcher.group("schema");
            url.user = matcher.group("user");
            url.password = matcher.group("password");
            url.host = matcher.group("host");
            url.port = matcher.group("port");
            String path = matcher.group("path");
            if (path.contains("?")) {
                int index = path.indexOf("?");
                url.path = path.substring(0, index);
                String params = path.substring(index + 1);
                url.params = KeyValuePairUtils.readAsMap(params);
            } else {
                url.path = path;
            }

        }
        return url;
    }

    public String getSchema() {
        return schema;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getParams() {
        return params;
    }


}
