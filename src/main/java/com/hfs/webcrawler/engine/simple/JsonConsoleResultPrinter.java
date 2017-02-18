package com.hfs.webcrawler.engine.simple;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hfs.webcrawler.data.UrlData;
import com.hfs.webcrawler.data.UrlDataMixIn;
import com.hfs.webcrawler.engine.ResultPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonConsoleResultPrinter implements ResultPrinter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonConsoleResultPrinter.class);

    private ObjectMapper objectMapper;

    public JsonConsoleResultPrinter() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void printResult(UrlData urlData) {
        try {
            objectMapper.addMixIn(UrlData.class, UrlDataMixIn.class);
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(urlData));
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
    }
}
