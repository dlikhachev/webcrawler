package com.hfs.webcrawler.engine;


import com.hfs.webcrawler.engine.data.UrlData;

/**
 * Data printer interface
 *
 * @param <T> class of the object that holds element data to print
 */
public interface DataPrinter<T> {

    /**
     * Start printing
     *
     * @param text message to print as starting
     */
    void startPrinting(String text);

    /**
     * Finish printing
     *
     * @param text message to print as final
     */
    void finishPrinting(String text);

    /**
     * Print data element
     *
     * @param urlData data element to print
     */
    void printElement(UrlData<T> urlData);

    /**
     * Print delimiter between elements
     */
    void printDelimiter();
}
