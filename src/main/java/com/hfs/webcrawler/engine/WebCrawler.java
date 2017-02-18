package com.hfs.webcrawler.engine;


public interface WebCrawler {

    void crawl(String urlToCrawl, boolean limitByDomainName, boolean includeChildLinks);
}
