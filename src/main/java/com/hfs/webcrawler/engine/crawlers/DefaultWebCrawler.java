package com.hfs.webcrawler.engine.crawlers;


import com.google.common.base.Strings;
import com.hfs.webcrawler.engine.data.UrlData;
import com.hfs.webcrawler.engine.DataPrinter;
import com.hfs.webcrawler.engine.WebLoader;
import com.hfs.webcrawler.engine.WebParser;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class DefaultWebCrawler extends AbstractWebCrawler {

    public DefaultWebCrawler(WebLoader<Document> webLoader,
                             WebParser<Document> webParser,
                             DataPrinter<Document> dataPrinter) {
        super(webLoader, webParser, dataPrinter);
    }

    @Override
    public void crawl(String urlToCrawl, boolean excludeChildUrls) {
        LOGGER.info("Starting crawling url {}", urlToCrawl);

        dataPrinter.startPrinting(String.format("Starting crawling %s, excluding child urls: %b ... \n", urlToCrawl, excludeChildUrls));

        try {
            this.setHostToCrawl(urlToCrawl);
            this.setExcludeChildUrls(excludeChildUrls);
            crawlUrl(urlToCrawl);
        } catch (URISyntaxException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            dataPrinter.finishPrinting(String.format("Can't get domain from url %s", urlToCrawl));
        }

        dataPrinter.finishPrinting("\n Finished crawling");
    }

    @Override
    protected void crawlUrl(String urlToCrawl) {
        if (Strings.isNullOrEmpty(urlToCrawl) || this.getHostToCrawl() == null) {
            return;
        }

        this.addToVisitedUrls(urlToCrawl);

        UrlData<Document> urlData = null;
        try {
            urlData = webLoader.load(urlToCrawl);
            urlData = webParser.parse(urlData);
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            dataPrinter.printElement(urlData);
        }

        if (urlData != null) {
            dataPrinter.printElement(urlData);

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
