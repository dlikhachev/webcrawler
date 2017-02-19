package com.hfs.webcrawler.engine;


import com.hfs.webcrawler.data.UrlData;

public interface DataPrinter<T> {

    void print(String text);

    void print(UrlData<T> urlData);
}
