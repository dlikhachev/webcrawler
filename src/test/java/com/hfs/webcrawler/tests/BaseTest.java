package com.hfs.webcrawler.tests;

import com.hfs.webcrawler.engine.WebCrawler;
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

public class BaseTest {

    protected String baseUri = "http://www.gocardless.com";
    protected String child1Url = "/page1.html";
    protected String asset1Name = "/js/script.js";
    protected String mainPageHtml = "<html><header><javascript src='" + asset1Name + "'><header><body><a href='" + child1Url + "'></html>";
    protected String child1PageHtml = "<html><header><body></html>";

    UrlData<Document> mainPageData;
    UrlData<Document> child1PageData;

    protected void prepareData() {
        mainPageData = new UrlData<>();
        mainPageData.setUrl(baseUri);
        mainPageData.setUrlData(Jsoup.parse(mainPageHtml, baseUri));

        child1PageData = new UrlData<>();
        child1PageData.setUrl(baseUri + child1Url);
        child1PageData.setUrlData(Jsoup.parse(child1PageHtml, baseUri));
    }

    protected DefaultWebCrawler prepareCrawler() throws IOException {
        prepareData();
        JSoupWebLoader loader = Mockito.mock(JSoupWebLoader.class);
        when(loader.load(baseUri)).thenReturn(mainPageData);
        when(loader.load(baseUri + child1Url)).thenReturn(child1PageData);
        return new DefaultWebCrawler(loader, new JSoupWebParser(), new JsonConsoleDataPrinter());
    }

    protected JSoupWebParser prepareParser() {
        prepareData();
        return new JSoupWebParser();
    }
}
