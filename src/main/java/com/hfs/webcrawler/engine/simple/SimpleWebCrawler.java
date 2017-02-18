package com.hfs.webcrawler.engine.simple;


import com.google.common.base.Strings;
import com.hfs.webcrawler.data.UrlData;
import com.hfs.webcrawler.engine.AbstractWebCrawler;
import com.hfs.webcrawler.engine.ResultPrinter;
import com.hfs.webcrawler.engine.WebParser;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class SimpleWebCrawler extends AbstractWebCrawler {


    public SimpleWebCrawler(WebParser webParser, ResultPrinter resultPrinter) {
        super(webParser, resultPrinter);
    }

    @Override
    public void crawl(String urlToCrawl, boolean includeChildUrls) throws URISyntaxException {
        super.crawl(urlToCrawl, includeChildUrls);
    }

    @Override
    protected void crawlUrl(String urlToCrawl) {
        LOGGER.info("Crawling url {urlToCrawl}", urlToCrawl);

        if (Strings.isNullOrEmpty(urlToCrawl) || this.getDomainToCrawl() == null) {
            return;
        }

        this.addToVisitedUrls(urlToCrawl);

        UrlData urlData = webParser.parseUrlData(urlToCrawl);

        resultPrinter.printResult(urlData);

        if (this.shouldChildUrlsBeIncluded()) {
            ArrayList<String> urls = urlData.getChildUrls();
            urls.parallelStream().
                    filter(this::isUrlBelongsToDomainToCrawl).
                    filter(this::isUrlNotVisited).
                    forEach(this::crawlUrl);
        }
    }
}
