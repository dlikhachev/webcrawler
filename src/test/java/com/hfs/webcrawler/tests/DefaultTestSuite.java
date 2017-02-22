package com.hfs.webcrawler.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UtilsTest.class,
        JSoupWebParserTest.class,
        DefaultWebCrawlerTest.class
})
public class DefaultTestSuite {
}
