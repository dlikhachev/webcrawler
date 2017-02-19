package com.hfs.webcrawler.support;


public class Utils {
    public static String addDefaultProtocolToUrl(String url) {
        if (!url.toLowerCase().matches("^\\w+://.*")) {
            url = "http://" + url;
        }
        return url;
    }
}
