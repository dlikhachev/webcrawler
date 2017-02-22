package com.hfs.webcrawler.engine.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Json annotations (MixIn) for UrlData class
 *
 * @param <T> class of the object that holds downloaded data
 */
public abstract class UrlDataMixIn<T> {

    @JsonProperty("url")
    public abstract String getUrl();

    @JsonProperty("assets")
    public abstract ArrayList<String> getAssets();

    @JsonIgnore
    public abstract ArrayList<String> getChildUrls();

    @JsonIgnore
    public abstract T getUrlData();
}
