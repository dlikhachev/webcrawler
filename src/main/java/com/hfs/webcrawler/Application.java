package com.hfs.webcrawler;

import com.hfs.webcrawler.engine.DataPrinter;
import com.hfs.webcrawler.engine.WebCrawler;
import com.hfs.webcrawler.engine.WebParser;
import com.hfs.webcrawler.engine.simple.ConsoleDataPrinter;
import com.hfs.webcrawler.engine.simple.SimpleWebCrawler;
import com.hfs.webcrawler.engine.simple.SimpleWebParser;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

import static org.springframework.beans.factory.config.PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE;

@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() throws IOException {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setSystemPropertiesMode(SYSTEM_PROPERTIES_MODE_OVERRIDE);
        return propertyPlaceholderConfigurer;
    }

    @Bean
    public static WebParser webPageParser() {
        return new SimpleWebParser();
    }

    @Bean
    public static DataPrinter resultPrinter() {
        return new ConsoleDataPrinter();
    }

    @Bean
    public static WebCrawler webCrawler(WebParser webParser, DataPrinter dataPrinter) {
        return new SimpleWebCrawler(webParser, dataPrinter);
    }
}
