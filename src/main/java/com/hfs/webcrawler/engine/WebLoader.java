package com.hfs.webcrawler.engine;

import com.hfs.webcrawler.engine.data.UrlData;

import java.io.IOException;

public interface WebLoader<T> {

    UrlData<T> load(String url) throws IOException;
}
