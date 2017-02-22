package com.hfs.webcrawler.engine.printers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hfs.webcrawler.engine.data.UrlData;
import com.hfs.webcrawler.engine.data.UrlDataMixIn;
import com.hfs.webcrawler.engine.DataPrinter;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Printer with output to console in form of json
 */
public class JsonConsoleDataPrinter implements DataPrinter<Document> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonConsoleDataPrinter.class);

    private ObjectMapper objectMapper;

    public JsonConsoleDataPrinter() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void startPrinting(String text) {
        System.out.println(text);
        System.out.println("[");
    }

    @Override
    public void finishPrinting(String text) {
        System.out.println("]");
        System.out.println(text);
    }

    @Override
    public void printElement(UrlData<Document> urlData) {
        try {
            objectMapper.addMixIn(UrlData.class, UrlDataMixIn.class);
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(urlData));
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public void printDelimiter() {
        System.out.println(",");
    }
}
