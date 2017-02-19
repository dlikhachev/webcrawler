package com.hfs.webcrawler.engine;

import com.hfs.webcrawler.data.UrlData;

import java.io.IOException;

public interface WebParser<T> {

    UrlData<T> parse(UrlData<T> urlData) throws IOException;
}
