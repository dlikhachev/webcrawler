package com.hfs.webcrawler;

import com.hfs.webcrawler.engine.WebCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WebCrawlerRunner implements CommandLineRunner {

    @Value("${startUrl:www.gocardless.com}")
    private String urlToCrawl;

    @Value("${includeChildUrls:true}")
    private boolean includeChildUrls;

    @Autowired
    private WebCrawler webCrawler;


    @Override
    public void run(String... strings) throws Exception {
        webCrawler.crawl(urlToCrawl, includeChildUrls);
    }
}
