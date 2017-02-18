package com.hfs.webcrawler.engine;


import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public abstract class AbstractWebCrawler implements WebCrawler {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractWebCrawler.class);

    private String domainToCrawl = null;
    private ArrayList<String> visitedUrls = new ArrayList<>();

    private boolean includeChildUrls = true;

    protected WebParser webParser;
    protected DataPrinter dataPrinter;


    public AbstractWebCrawler(WebParser webParser,
                              DataPrinter dataPrinter) {
        this.webParser = webParser;
        this.dataPrinter = dataPrinter;
    }

    @Override
    public abstract void crawl(String urlToCrawl, boolean includeChildUrls);

    protected abstract void crawlUrl(String urlToCrawl);


    protected String getDomainToCrawl() {
        return this.domainToCrawl;
    }

    //TODO handle exceptions
    protected void setDomainToCrawl(String urlToCrawl) throws URISyntaxException {
        this.domainToCrawl = new URI(urlToCrawl.toLowerCase()).getHost();
        LOGGER.info("Setting domain to crawl {domainToCrawl}", domainToCrawl);
    }

    protected boolean isUrlBelongsToDomainToCrawl(String url) {
        return !Strings.isNullOrEmpty(url) && url.toLowerCase().contains(this.domainToCrawl);
    }

    protected void addToVisitedUrls(String urlToAdd) {
        if (!Strings.isNullOrEmpty(urlToAdd)) {
            String url = urlToAdd.toLowerCase();
            if (!visitedUrls.contains(url))
                visitedUrls.add(url);
        }
    }

    protected boolean isUrlNotVisited(String url) {
        return !Strings.isNullOrEmpty(url) && !visitedUrls.contains(url.toLowerCase());
    }


    protected boolean shouldChildUrlsBeIncluded() {
        return includeChildUrls;
    }

    protected void setIncludeChildUrls(boolean includeChildUrls) {
        this.includeChildUrls = includeChildUrls;
    }
}
