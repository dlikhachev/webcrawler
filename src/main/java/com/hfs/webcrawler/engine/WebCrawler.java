package com.hfs.webcrawler.engine;


/**
 * Web Crawler interface
 */
public interface WebCrawler {

    /**
     * Start web crawling
     *
     * @param urlToCrawl       url to start crawling
     * @param includeChildUrls include or exclude child urls from crawling
     */
    void crawl(String urlToCrawl, boolean includeChildUrls);
}
