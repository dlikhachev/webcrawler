package com.hfs.webcrawler.engine.simple;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hfs.webcrawler.data.UrlData;
import com.hfs.webcrawler.data.UrlDataMixIn;
import com.hfs.webcrawler.engine.DataPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleDataPrinter implements DataPrinter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleDataPrinter.class);

    private ObjectMapper objectMapper;

    public ConsoleDataPrinter() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void print(String text) {
        System.out.println(text);
    }

    @Override
    public void print(UrlData urlData) {
        try {
            objectMapper.addMixIn(UrlData.class, UrlDataMixIn.class);
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(urlData));
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
    }
}
