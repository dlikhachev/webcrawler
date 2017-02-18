package com.hfs.webcrawler.engine;

import com.hfs.webcrawler.data.UrlData;

public interface WebParser {

    UrlData parseUrlData(String url);
}
