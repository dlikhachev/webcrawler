package com.hfs.webcrawler.engine;

import com.hfs.webcrawler.data.PageData;

public interface WebPageParser {

    PageData parseWebPage(String url);
}
