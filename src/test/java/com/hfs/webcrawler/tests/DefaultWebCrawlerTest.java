package com.hfs.webcrawler.tests;


import com.hfs.webcrawler.engine.crawlers.DefaultWebCrawler;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class DefaultWebCrawlerTest extends BaseTest {

    @Test
    public void testCrawlLogicIncludingChilds() throws IOException {
        DefaultWebCrawler crawler = prepareCrawler();

        crawler.crawl(baseUri, false);
        List<String> visitedUrls = crawler.getVisitedUrls();
        Assert.assertNotNull(visitedUrls);
        Assert.assertEquals(2, visitedUrls.size());
        Assert.assertEquals(baseUri, visitedUrls.get(0));
        Assert.assertEquals(baseUri + child1Url, visitedUrls.get(1));
    }

    @Test
    public void testCrawlLogicExcludingChilds() throws IOException {
        DefaultWebCrawler crawler = prepareCrawler();

        crawler.crawl(baseUri, true);
        List<String> visitedUrls = crawler.getVisitedUrls();
        Assert.assertNotNull(visitedUrls);
        Assert.assertEquals(1, visitedUrls.size());
        Assert.assertEquals(baseUri, visitedUrls.get(0));
    }
}