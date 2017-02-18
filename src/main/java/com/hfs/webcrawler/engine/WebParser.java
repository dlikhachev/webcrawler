package com.hfs.webcrawler.engine;

import com.hfs.webcrawler.data.UrlData;

import java.io.IOException;

public interface WebParser {

    UrlData parseUrlData(String url) throws IOException;
}
