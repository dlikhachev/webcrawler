package com.hfs.webcrawler.tests;


import com.hfs.webcrawler.engine.bolts.jsoup.JSoupWebLoader;
import com.hfs.webcrawler.engine.data.UrlData;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class JSoupWebLoaderTest {

    @Test
    public void testLoadResponseFormat() throws IOException {
        JSoupWebLoader webLoader = new JSoupWebLoader();
        UrlData<Document> urlData = webLoader.load("www.gocardless.com");
        Assert.assertNotNull(urlData);
        Assert.assertEquals(urlData.getUrl(), "http://www.gocardless.com");
        Assert.assertNotNull(urlData.getUrlData());
    }
}