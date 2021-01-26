package com.github.caijh.commons.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlInfo {

    public static final Pattern URL_PATTERN = Pattern
        .compile("((?<schema>\\w+)://)?((?<user>\\w+):(?<password>\\w+)@)?(?<host>[^/:]+)(:(?<port>\\d*))?(?<path>[^ ]*)");
    private String schema;
    private String user;
    private String password;
    private String host;
    private String port;
    private String path;
    private Map<String, String> params;

    private UrlInfo() {}

    public static UrlInfo from(String urlString) {
        Matcher matcher = URL_PATTERN.matcher(urlString);
        UrlInfo urlInfo = null;
        if (matcher.matches()) {
            urlInfo = new UrlInfo();
            urlInfo.schema = matcher.group("schema");
            urlInfo.user = matcher.group("user");
            urlInfo.password = matcher.group("password");
            urlInfo.host = matcher.group("host");
            urlInfo.port = matcher.group("port");
            String path = matcher.group("path");
            if (path.contains("?")) {
                int index = path.indexOf("?");
                urlInfo.path = path.substring(0, index);
                String params = path.substring(index + 1);
                urlInfo.params = KeyValuePairUtils.readAsMap(params);
            } else {
                urlInfo.path = path;
            }

        }
        return urlInfo;
    }

    public static String getParam(String url, String key) {
        UrlInfo urlInfo = from(url);
        if (urlInfo != null) {
            return urlInfo.getParams().get(key);
        }
        return null;
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
