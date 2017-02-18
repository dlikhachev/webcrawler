package com.hfs.webcrawler.engine;


import java.net.URISyntaxException;

public interface WebCrawler {

    void crawl(String urlToCrawl, boolean includeChildUrls) throws URISyntaxException;
}
