package com.hfs.webcrawler.engine;


import java.util.ArrayList;

public abstract class AbstractWebCrawler implements WebCrawler {

    private ArrayList<String> visitedLinks = new ArrayList<>();
    private boolean limitByDomainName = true;
    private  boolean includeChildLinks = true;

    @Override
    public abstract void crawl(String urlToCrawl, boolean limitByDomainName, boolean includeChildLinks);

    protected void addVisitedLink(String link) {
        visitedLinks.add(link);
    }

    protected boolean isAlreadyVisited(String link) {
        return visitedLinks.contains(link);
    }
}
