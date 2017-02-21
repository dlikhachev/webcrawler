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
        Assert.assertEquals(visitedUrls.size(), 2);
        Assert.assertEquals(visitedUrls.get(0), baseUri);
        Assert.assertEquals(visitedUrls.get(1), baseUri + child1Url);
    }

    @Test
    public void testCrawlLogicExcludingChilds() throws IOException {
        DefaultWebCrawler crawler = prepareCrawler();

        crawler.crawl(baseUri, true);
        List<String> visitedUrls = crawler.getVisitedUrls();
        Assert.assertNotNull(visitedUrls);
        Assert.assertEquals(visitedUrls.size(), 1);
        Assert.assertEquals(visitedUrls.get(0), baseUri);
    }
}