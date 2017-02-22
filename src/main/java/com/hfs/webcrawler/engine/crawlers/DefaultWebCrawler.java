package com.hfs.webcrawler.engine.crawlers;


import com.hfs.webcrawler.engine.data.UrlData;
import com.hfs.webcrawler.engine.DataPrinter;
import com.hfs.webcrawler.engine.WebLoader;
import com.hfs.webcrawler.engine.WebParser;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Default web crawler
 */
public class DefaultWebCrawler extends AbstractWebCrawler {

    public DefaultWebCrawler(WebLoader<Document> webLoader,
                             WebParser<Document> webParser,
                             DataPrinter<Document> dataPrinter) {
        super(webLoader, webParser, dataPrinter);
    }

    @Override
    public void crawl(String urlToCrawl, boolean excludeChildUrls) {
        LOGGER.info("Starting crawling url {}", urlToCrawl);

        printer.startPrinting(String.format("Starting crawling %s, excluding child urls: %b ... \n", urlToCrawl, excludeChildUrls));

        try {
            this.setHostToCrawl(urlToCrawl);
            this.setExcludeChildUrls(excludeChildUrls);
            this.resetVisitedUrls();
            crawlUrl(urlToCrawl);
        } catch (URISyntaxException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            printer.finishPrinting(String.format("Can't get domain from url %s", urlToCrawl));
        }

        printer.finishPrinting("\n Finished crawling");
    }

    @Override
    protected void crawlUrl(String urlToCrawl) {
        this.addToVisitedUrls(urlToCrawl);
        if (getVisitedUrls().size() > 1) {
            printer.printDelimiter();
        }

        UrlData<Document> urlData = null;
        try {
            urlData = loader.load(urlToCrawl);
            urlData = parser.parse(urlData);
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            printer.printElement(urlData);
        }

        if (urlData != null) {
            printer.printElement(urlData);

            if (!this.shouldExcludeChildUrls()) {
                ArrayList<String> urls = urlData.getChildUrls();
                urls.parallelStream().
                        filter(this::isUrlBelongsToHostToCrawl).
                        filter(this::isUrlNotVisited).
                        forEach(this::crawlUrl);
            }
        }
    }
}
