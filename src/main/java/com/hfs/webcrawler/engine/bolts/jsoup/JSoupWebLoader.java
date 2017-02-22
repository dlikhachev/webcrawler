package com.hfs.webcrawler.engine.bolts.jsoup;

import com.hfs.webcrawler.engine.data.UrlData;
import com.hfs.webcrawler.engine.WebLoader;
import com.hfs.webcrawler.support.Utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


/**
 * JSoup implementation of WebLoader
 */
public class JSoupWebLoader implements WebLoader<Document> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSoupWebLoader.class);

    @Override
    public UrlData<Document> load(String url) {
        LOGGER.info("Loading url {}", url);

        url = Utils.addDefaultProtocolToUrl(url);

        UrlData<Document> data = new UrlData<>();
        data.setUrl(url);

        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            data.setLoadingError(e.toString());
        }
        data.setData(document);

        return data;
    }
}
