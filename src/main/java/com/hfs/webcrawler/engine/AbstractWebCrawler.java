package com.hfs.webcrawler.engine;


import com.google.common.base.Strings;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public abstract class AbstractWebCrawler implements WebCrawler {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractWebCrawler.class);

    private String domainToCrawl = null;
    private ArrayList<String> visitedUrls = new ArrayList<>();

    private boolean excludeChildUrls = true;

    protected WebLoader<Document> webLoader;
    protected WebParser<Document> webParser;
    protected DataPrinter<Document> dataPrinter;


    public AbstractWebCrawler(WebLoader<Document> webLoader,
                              WebParser<Document> webParser,
                              DataPrinter<Document> dataPrinter) {
        this.webLoader = webLoader;
        this.webParser = webParser;
        this.dataPrinter = dataPrinter;
    }

    @Override
    public abstract void crawl(String urlToCrawl, boolean excludeChildUrls);

    protected abstract void crawlUrl(String urlToCrawl);


    protected String getDomainToCrawl() {
        return this.domainToCrawl;
    }

    protected void setDomainToCrawl(String urlToCrawl) throws URISyntaxException {
        URI uriToCrawl = new URI(urlToCrawl.toLowerCase());
        this.domainToCrawl = uriToCrawl.getHost();
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

    public ArrayList<String> getVisitedUrls() {
        return visitedUrls;
    }


    protected boolean shouldExcludeChildUrls() {
        return excludeChildUrls;
    }

    protected void setExcludeChildUrls(boolean excludeChildUrls) {
        this.excludeChildUrls = excludeChildUrls;
    }
}
