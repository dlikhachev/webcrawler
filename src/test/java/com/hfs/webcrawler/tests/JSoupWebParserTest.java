package com.hfs.webcrawler.tests;


import com.hfs.webcrawler.engine.bolts.jsoup.JSoupWebParser;
import com.hfs.webcrawler.engine.data.UrlData;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class JSoupWebParserTest extends BaseTest {

    @Test
    public void testParse() throws IOException {
        JSoupWebParser parser = prepareParser();
        UrlData<Document> data = parser.parse(mainPageData);
        Assert.assertNotNull(data);
        Assert.assertEquals(1, data.getChildUrls().size());
        Assert.assertEquals(baseUri + child1Url, data.getChildUrls().get(0));
        Assert.assertEquals(1, data.getAssets().size());
        Assert.assertEquals(baseUri + asset1Name, data.getAssets().get(0));
    }
}