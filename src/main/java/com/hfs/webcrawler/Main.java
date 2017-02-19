package com.hfs.webcrawler;

import com.hfs.webcrawler.engine.impl.ConsoleDataPrinter;
import com.hfs.webcrawler.engine.impl.DefaultWebCrawler;
import com.hfs.webcrawler.engine.impl.JSoupWebLoader;
import com.hfs.webcrawler.engine.impl.JSoupWebParser;
import org.apache.commons.cli.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Options options = getOptions();
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("java -jar web-crawler.jar [params]", options);

            System.exit(1);
            return;
        }

        String urlToCrawl = cmd.getOptionValue("urlToCrawl");
        boolean excludeChildUrls = cmd.hasOption("excludeChildUrls");

        DefaultWebCrawler webCrawler = new DefaultWebCrawler(
                new JSoupWebLoader(),
                new JSoupWebParser(),
                new ConsoleDataPrinter());

        webCrawler.crawl(urlToCrawl, excludeChildUrls);
    }

    private static Options getOptions() {
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
