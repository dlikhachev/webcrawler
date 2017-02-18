package com.hfs.webcrawler.engine.simple;


import com.hfs.webcrawler.ClientRunner;
import com.hfs.webcrawler.engine.AbstractWebCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleWebCrawler extends AbstractWebCrawler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientRunner.class);


    @Override
    public void crawl(String urlToCrawl, boolean limitByDomainName, boolean includingChildLinks) {

    }
}
