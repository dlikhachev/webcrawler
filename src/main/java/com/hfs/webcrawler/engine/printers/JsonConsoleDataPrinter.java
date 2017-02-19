package com.hfs.webcrawler.engine.printers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hfs.webcrawler.engine.data.UrlData;
import com.hfs.webcrawler.engine.data.UrlDataMixIn;
import com.hfs.webcrawler.engine.DataPrinter;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonConsoleDataPrinter implements DataPrinter<Document> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonConsoleDataPrinter.class);

    private ObjectMapper objectMapper;

    public JsonConsoleDataPrinter() {
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
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
