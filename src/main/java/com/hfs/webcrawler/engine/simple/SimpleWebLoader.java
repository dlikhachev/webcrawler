package com.hfs.webcrawler.engine.simple;

import com.hfs.webcrawler.data.UrlData;
import com.hfs.webcrawler.engine.WebLoader;
import com.hfs.webcrawler.engine.WebParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class SimpleWebLoader implements WebLoader<Document> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleWebLoader.class);

    @Override
    public UrlData<Document> load(String url) throws IOException {
        LOGGER.info("Loading url {url}", url);

        UrlData<Document> urlData = new UrlData<>();
        urlData.setUrl(url);

        Document document = Jsoup.connect(url).get();
        urlData.setUrlData(document);

        return urlData;
    }
}
