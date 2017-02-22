package com.hfs.webcrawler;

import com.hfs.webcrawler.engine.printers.JsonConsoleDataPrinter;
import com.hfs.webcrawler.engine.crawlers.DefaultWebCrawler;
import com.hfs.webcrawler.engine.bolts.jsoup.JSoupWebLoader;
import com.hfs.webcrawler.engine.bolts.jsoup.JSoupWebParser;
import org.apache.commons.cli.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Options options = buildOptions();
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("java -jar web-crawler-1.0-jar-with-dependencies.jar [params]", options);

            System.exit(1);
            return;
        }

        String urlToCrawl = cmd.getOptionValue("urlToCrawl");
        boolean excludeChildUrls = cmd.hasOption("excludeChildUrls");

        DefaultWebCrawler crawler = new DefaultWebCrawler(
                new JSoupWebLoader(),
                new JSoupWebParser(),
                new JsonConsoleDataPrinter());
        crawler.crawl(urlToCrawl, excludeChildUrls);
    }

    private static Options buildOptions() {
        Options options = new Options();

        Option urlToCrawl = new Option("urlToCrawl", true, "http url to crawl");
        urlToCrawl.setRequired(true);
        options.addOption(urlToCrawl);

        Option excludeChildUrls = new Option("excludeChildUrls", false, "exclude child urls from crawling");
        excludeChildUrls.setRequired(false);
        options.addOption(excludeChildUrls);
        return options;
    }
}
