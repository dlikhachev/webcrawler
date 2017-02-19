package com.hfs.webcrawler.engine;


import com.hfs.webcrawler.engine.data.UrlData;

public interface DataPrinter<T> {

    void startPrinting(String text);

    void finishPrinting(String text);

    void printElement(UrlData<T> urlData);

    void printDelimiter();
}
