package com.hfs.webcrawler.tests;

import com.hfs.webcrawler.engine.bolts.jsoup.JSoupWebLoader;
import com.hfs.webcrawler.engine.bolts.jsoup.JSoupWebParser;
import com.hfs.webcrawler.engine.crawlers.DefaultWebCrawler;
import com.hfs.webcrawler.engine.data.UrlData;
import com.hfs.webcrawler.engine.printers.JsonConsoleDataPrinter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.when;

class BaseTest {
    String baseUri = "http://www.gocardless.com";
    String baseUriWithoutProtocol = "www.gocardless.com";
    String baseUriSecured = "https://www.gocardless.com";
    String child1Url = "/page1.html";
    String asset1Name = "/js/script.js";
    private String mainPageHtml = "<html><head><script src='" + asset1Name + "'></script></head><body><a href='" + child1Url + "'></a></body></html>";
    private String child1PageHtml = "<html><head></head><body></body></html>";

    UrlData<Document> mainPageData;
    UrlData<Document> child1PageData;

    private void prepareData() {
        mainPageData = new UrlData<>();
        mainPageData.setUrl(baseUri);
        mainPageData.setUrlData(Jsoup.parse(mainPageHtml, baseUri));

        child1PageData = new UrlData<>();
        child1PageData.setUrl(baseUri + child1Url);
        child1PageData.setUrlData(Jsoup.parse(child1PageHtml, baseUri));
    }

    DefaultWebCrawler prepareCrawler() throws IOException {
        prepareData();
        JSoupWebLoader loader = Mockito.mock(JSoupWebLoader.class);
        when(loader.load(baseUri)).thenReturn(mainPageData);
        when(loader.load(baseUri + child1Url)).thenReturn(child1PageData);
        return new DefaultWebCrawler(loader, new JSoupWebParser(), new JsonConsoleDataPrinter());
    }

    JSoupWebParser prepareParser() {
        prepareData();
        return new JSoupWebParser();
    }
}
