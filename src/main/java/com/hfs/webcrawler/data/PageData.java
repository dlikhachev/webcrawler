package com.hfs.webcrawler.data;


import java.util.ArrayList;

public class PageData {

    private ArrayList<String> links;
    private ArrayList<String> sources;

    public PageData() {
        this.links = new ArrayList<>();
        this.sources = new ArrayList<>();
    }

    public ArrayList<String> getLinks() {
        return links;
    }

    public ArrayList<String> getSources() {
        return sources;
    }
}
