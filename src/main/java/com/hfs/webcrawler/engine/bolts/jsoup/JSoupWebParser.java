package com.hfs.webcrawler.engine.bolts.jsoup;

import com.hfs.webcrawler.engine.data.UrlData;
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
    public UrlData<Document> parse(UrlData<Document> data) {
        LOGGER.info("Parsing url {url}", data.getUrl());

        Document document = data.getUrlData();
        fetchAssets(document, data);
        fetchChildUrls(document, data);

        return data;
    }

    private void fetchAssets(Document document, UrlData data) {
        Elements media = document.select("[src]");
        for (Element link : media) {
            data.addToAssets(link.attr("abs:src"));
        }

        Elements imports = document.select("link[href]");
        for (Element link : imports) {
            data.addToAssets(link.attr("abs:href"));
        }
    }

    private void fetchChildUrls(Document document, UrlData data) {
        Elements links = document.select("a[href]");
        for (Element link : links) {
            data.addToChildUrls(link.attr("abs:href"));
        }
    }
}
