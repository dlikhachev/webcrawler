package com.hfs.webcrawler.engine.simple;


import com.google.common.base.Strings;
import com.hfs.webcrawler.data.UrlData;
import com.hfs.webcrawler.engine.AbstractWebCrawler;
import com.hfs.webcrawler.engine.DataPrinter;
import com.hfs.webcrawler.engine.WebParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class SimpleWebCrawler extends AbstractWebCrawler {

    public SimpleWebCrawler(WebParser webParser, DataPrinter dataPrinter) {
        super(webParser, dataPrinter);
    }

    @Override
    public void crawl(String urlToCrawl, boolean includeChildUrls) {
        LOGGER.info("Starting crawling url {urlToCrawl}", urlToCrawl);

        //TODO check String.format();
        dataPrinter.print(String.format("Starting crawling %s, include child urls: %b ...", urlToCrawl, includeChildUrls));

        try {
            this.setDomainToCrawl(urlToCrawl);
            this.setIncludeChildUrls(includeChildUrls);
            crawlUrl(urlToCrawl);
        } catch (URISyntaxException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            dataPrinter.print(String.format("Can't get domain from url %s", urlToCrawl));
        }

        dataPrinter.print("Finished crawling.");
    }

    @Override
    protected void crawlUrl(String urlToCrawl) {
        if (Strings.isNullOrEmpty(urlToCrawl) || this.getDomainToCrawl() == null) {
            dataPrinter.print(String.format("Empty url for crawling: %s. Skipping ...", urlToCrawl));
            return;
        }

        this.addToVisitedUrls(urlToCrawl);

        UrlData urlData = null;
        try {
            urlData = webParser.parseUrlData(urlToCrawl);
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            dataPrinter.print(String.format("Can't get data for: %s. Skipping ...", urlToCrawl));
        }

        if (urlData != null) {
            dataPrinter.print(urlData);

            if (this.shouldChildUrlsBeIncluded()) {
                ArrayList<String> urls = urlData.getChildUrls();
                urls.parallelStream().
                        filter(this::isUrlBelongsToDomainToCrawl).
                        filter(this::isUrlNotVisited).
                        forEach(this::crawlUrl);
            }
        }
    }
}
