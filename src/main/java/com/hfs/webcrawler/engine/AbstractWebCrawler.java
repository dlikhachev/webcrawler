package com.hfs.webcrawler.engine;


import com.google.common.base.Strings;
import com.hfs.webcrawler.support.Utils;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public abstract class AbstractWebCrawler implements WebCrawler {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractWebCrawler.class);

    private String hostToCrawl = null;
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


    protected String getHostToCrawl() {
        return this.hostToCrawl;
    }

    protected void setHostToCrawl(String urlToCrawl) throws URISyntaxException {
        URI uri = new URI(Utils.addDefaultProtocolToUrl(urlToCrawl));
        this.hostToCrawl = uri.getHost().toLowerCase();
        LOGGER.info("Setting host to crawl {hostToCrawl}", hostToCrawl);
    }

    protected boolean isUrlBelongsToHostToCrawl(String url) {
        return !Strings.isNullOrEmpty(url) && url.toLowerCase().contains(this.hostToCrawl);
    }

    protected void addToVisitedUrls(String urlToAdd) {
        if (!Strings.isNullOrEmpty(urlToAdd)) {
            String url = Utils.addDefaultProtocolToUrl(urlToAdd.toLowerCase());
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
