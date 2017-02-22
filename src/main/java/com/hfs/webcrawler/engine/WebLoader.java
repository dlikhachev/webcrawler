package com.hfs.webcrawler.engine;

import com.hfs.webcrawler.engine.data.UrlData;

import java.io.IOException;

/**
 * Web loader interface
 *
 * @param <T> class of the object which holds data loaded from web
 */
public interface WebLoader<T> {

    /**
     * Load data from web
     *
     * @param url url to load data from
     * @return object that holds loaded web data
     */
    UrlData<T> load(String url);
}
