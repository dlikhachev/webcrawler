package com.hfs.webcrawler.engine.crawlers;


import com.google.common.base.Strings;
import com.hfs.webcrawler.engine.DataPrinter;
import com.hfs.webcrawler.engine.WebCrawler;
import com.hfs.webcrawler.engine.WebLoader;
import com.hfs.webcrawler.engine.WebParser;
import com.hfs.webcrawler.support.Utils;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Base web crawler
 */
public abstract class AbstractWebCrawler implements WebCrawler {

    static final Logger LOGGER = LoggerFactory.getLogger(AbstractWebCrawler.class);

    /**
     * Url to crawl
     */
    private String hostToCrawl = null;
    /**
     * List of already visited urls
     */
    private ArrayList<String> visitedUrls = new ArrayList<>();
    /**
     * Exclude childs from crawling process
     */
    private boolean excludeChildUrls = true;

    WebLoader<Document> loader;
    WebParser<Document> parser;
    DataPrinter<Document> printer;


    public AbstractWebCrawler(WebLoader<Document> loader,
                              WebParser<Document> parser,
                              DataPrinter<Document> printer) {
        this.loader = loader;
        this.parser = parser;
        this.printer = printer;
    }

    @Override
    public abstract void crawl(String urlToCrawl, boolean excludeChildUrls);

    /**
     * Main crawl logic
     *
     * @param urlToCrawl url to crawl
     */
    protected abstract void crawlUrl(String urlToCrawl);


    String getHostToCrawl() {
        return this.hostToCrawl;
    }

    void setHostToCrawl(String urlToCrawl) throws URISyntaxException {
        URI uri = new URI(Utils.addDefaultProtocolToUrl(urlToCrawl));
        this.hostToCrawl = uri.getHost().toLowerCase();
        LOGGER.info("Setting host to crawl {hostToCrawl}", hostToCrawl);
    }

    boolean isUrlBelongsToHostToCrawl(String url) {
        return !Strings.isNullOrEmpty(url) && url.toLowerCase().contains(this.hostToCrawl);
    }


    void addToVisitedUrls(String urlToAdd) {
        if (!Strings.isNullOrEmpty(urlToAdd)) {
            String url = Utils.addDefaultProtocolToUrl(urlToAdd.toLowerCase());
            if (!visitedUrls.contains(url))
                visitedUrls.add(url);
        }
    }

    void resetVisitedUrls() {
        visitedUrls.clear();
    }

    boolean isUrlNotVisited(String url) {
        return !Strings.isNullOrEmpty(url) && !visitedUrls.contains(url.toLowerCase());
    }

    public ArrayList<String> getVisitedUrls() {
        return visitedUrls;
    }


    boolean shouldExcludeChildUrls() {
        return excludeChildUrls;
    }

    void setExcludeChildUrls(boolean excludeChildUrls) {
        this.excludeChildUrls = excludeChildUrls;
    }
}
