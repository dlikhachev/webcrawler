package com.hfs.webcrawler.engine.impl;

import com.hfs.webcrawler.data.UrlData;
import com.hfs.webcrawler.engine.WebParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class JSoupWebParser implements WebParser<Document> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSoupWebParser.class);

    @Override
    public UrlData<Document> parse(UrlData<Document> urlData) throws IOException {
        LOGGER.info("Parsing url {url}", urlData.getUrl());

        Document document = urlData.getUrlData();
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
