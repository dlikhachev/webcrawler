package com.hfs.webcrawler.engine.bolts.jsoup;

import com.hfs.webcrawler.engine.data.UrlData;
import com.hfs.webcrawler.engine.WebParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * JSoup implementation of WebParser
 */
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
        Elements stylesheets = document.select("link[href][rel='stylesheet']");
        for (Element stylesheet : stylesheets) {
            data.addToAssets(stylesheet.attr("abs:href"));
        }

        Elements scripts = document.select("script[src]");
        for (Element script : scripts) {
            data.addToAssets(script.attr("abs:src"));
        }

        Elements images = document.select("img[src]");
        for (Element image : images) {
            data.addToAssets(image.attr("abs:src"));
        }

        Elements icons = document.select("link[href][rel*='icon']");
        for (Element icon : icons) {
            data.addToAssets(icon.attr("abs:href"));
        }

        Elements meta_icons = document.select("meta[content*='.png']");
        for (Element meta_icon : meta_icons) {
            data.addToAssets(meta_icon.attr("abs:content"));
        }
    }

    private void fetchChildUrls(Document document, UrlData data) {
        Elements links = document.select("a[href]");
        for (Element link : links) {
            data.addToChildUrls(link.attr("abs:href"));
        }
    }
}
