package com.hfs.webcrawler.engine.simple;

import com.hfs.webcrawler.data.UrlData;
import com.hfs.webcrawler.engine.WebParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class SimpleWebParser implements WebParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleWebParser.class);

    @Override
    public UrlData parseUrlData(String url) throws IOException {
        LOGGER.info("Parsing url {url}", url);

        UrlData urlData = new UrlData();
        urlData.setUrl(url);

        Document document = Jsoup.connect(url).get();
        fetchAssets(document, urlData);
        fetchChildUrls(document, urlData);

        return urlData;
    }

    private void fetchAssets(Document document, UrlData urlData) {
        Elements media = document.select("[src]");
        for (Element link : media) {
            urlData.addToAssets(link.attr("abs:src"));
        }

        Elements imports = document.select("link[href]");
        for (Element link : imports) {
            urlData.addToAssets(link.attr("abs:href"));
        }
    }

    private void fetchChildUrls(Document document, UrlData urlData) {
        Elements links = document.select("a[href]");
        for (Element link : links) {
            urlData.addToChildUrls(link.attr("abs:href"));
        }
    }
}
