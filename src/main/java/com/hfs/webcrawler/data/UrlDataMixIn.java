package com.hfs.webcrawler.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public abstract class UrlDataMixIn {

    @JsonProperty("url")
    public abstract String getUrl();

    @JsonProperty("assets")
    public abstract ArrayList<String> getAssets();

    @JsonIgnore
    public abstract ArrayList<String> getChildUrls();
}
