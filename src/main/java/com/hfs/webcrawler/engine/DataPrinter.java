package com.hfs.webcrawler.engine;


import com.hfs.webcrawler.engine.data.UrlData;
import org.jsoup.nodes.Document;


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
     * @param data data element to print
     */
    void printElement(UrlData<T> data);

    /**
     * Print delimiter between elements
     */
    void printDelimiter();

    /**
     * Print error
     *
     * @param data data element to print error for
     */
    void printError(UrlData<Document> data);
}
