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
        Assert.assertEquals(data.getChildUrls().size(), 1);
        Assert.assertEquals(data.getChildUrls().get(0), baseUri + child1Url);
        Assert.assertEquals(data.getAssets().size(), 1);
        Assert.assertEquals(data.getAssets().get(0), baseUri + asset1Name);
    }
}