package com.hfs.webcrawler.engine;

import com.hfs.webcrawler.engine.data.UrlData;


/**
 * Web parser interface
 *
 * @param <T> class of the object which holds data to parse
 */
public interface WebParser<T> {

    /**
     * Parse url data
     *
     * @param data url data to parse
     * @return collection with assets and child urls of parsed url
     */
    UrlData<T> parse(UrlData<T> data);
}
