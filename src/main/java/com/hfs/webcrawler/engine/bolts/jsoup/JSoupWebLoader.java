package com.hfs.webcrawler.engine.bolts.jsoup;

import com.hfs.webcrawler.engine.data.UrlData;
import com.hfs.webcrawler.engine.WebLoader;
import com.hfs.webcrawler.support.Utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class JSoupWebLoader implements WebLoader<Document> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSoupWebLoader.class);

    @Override
    public UrlData<Document> load(String url) throws IOException {
        LOGGER.info("Loading url {url}", url);

        url = Utils.addDefaultProtocolToUrl(url);

        UrlData<Document> urlData = new UrlData<>();
        urlData.setUrl(url);

        Document document = Jsoup.connect(url).get();
        urlData.setUrlData(document);

        return urlData;
    }
}
