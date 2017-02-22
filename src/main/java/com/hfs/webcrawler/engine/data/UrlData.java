package com.hfs.webcrawler.engine.data;


import com.google.common.base.Strings;

import java.util.ArrayList;

/**
 * Data structure to keep url data
 *
 * @param <T> class of the object that holds downloaded data
 */
public class UrlData<T> {

    /**
     * Url, which data is loaded and parsed
     */
    private String url;
    /**
     * Raw data, loaded from url
     */
    private T urlData;
    /**
     * List of child urls parsed from url data
     */
    private ArrayList<String> childUrls;
    /**
     * List of assets parsed from url data
     */
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


    public T getUrlData() {
        return urlData;
    }

    public void setUrlData(T urlData) {
        this.urlData = urlData;
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
