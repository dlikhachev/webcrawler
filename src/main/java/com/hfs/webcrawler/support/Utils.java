package com.hfs.webcrawler.support;


/**
 * Support Utils
 */
public class Utils {

    /**
     * Add default protocol (http) to url without protocol
     *
     * @param url url to check and add protocol
     * @return updated url
     */
    public static String addDefaultProtocolToUrl(String url) {
        if (!url.toLowerCase().matches("^\\w+://.*")) {
            url = "http://" + url;
        }
        return url;
    }
}
