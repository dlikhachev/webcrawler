package com.hfs.webcrawler.engine.crawlers;


import com.hfs.webcrawler.engine.data.UrlData;
import com.hfs.webcrawler.engine.DataPrinter;
import com.hfs.webcrawler.engine.WebLoader;
import com.hfs.webcrawler.engine.WebParser;
import org.jsoup.nodes.Document;

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

        UrlData<Document> data = loader.load(urlToCrawl);
        if (data.getData() != null) {
            data = parser.parse(data);
            printer.printElement(data);

            if (!this.shouldExcludeChildUrls()) {
                ArrayList<String> urls = data.getChildUrls();
                urls.parallelStream().
                        filter(this::isUrlBelongsToHostToCrawl).
                        filter(this::isUrlNotVisited).
                        forEach(this::crawlUrl);
            }
        } else {
            printer.printError(data);
        }
    }
}
