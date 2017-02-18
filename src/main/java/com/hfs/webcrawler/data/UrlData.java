package com.hfs.webcrawler.data;


import com.google.common.base.Strings;

import java.util.ArrayList;

public class UrlData {

    private String url;
    private ArrayList<String> childUrls;

    private ArrayList<String> assets;

    public UrlData() {
        this.childUrls = new ArrayList<>();
        this.assets = new ArrayList<>();
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public ArrayList<String> getChildUrls() {
        return childUrls;
    }

    public void addToChildUrls(String urlToAdd) {
        if (!Strings.isNullOrEmpty(urlToAdd)) {
            String url = urlToAdd.toLowerCase();
            if (!childUrls.contains(url))
                childUrls.add(url);
        }
    }


    public ArrayList<String> getAssets() {
        return assets;
    }

    public void addToAssets(String assetToAdd) {
        if (!Strings.isNullOrEmpty(assetToAdd)) {
            String asset = assetToAdd.toLowerCase();
            if (!assets.contains(asset))
                assets.add(asset);
        }
    }
}
