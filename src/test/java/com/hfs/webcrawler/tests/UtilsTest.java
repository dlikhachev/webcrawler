package com.hfs.webcrawler.tests;


import com.hfs.webcrawler.support.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class UtilsTest extends BaseTest {

    @Test
    public void testAddDefaultProtocolToUrl() throws IOException {
        String url = Utils.addDefaultProtocolToUrl(baseUri);
        Assert.assertNotNull(url);
        Assert.assertEquals(baseUri, url);

        url = Utils.addDefaultProtocolToUrl(baseUriWithoutProtocol);
        Assert.assertNotNull(url);
        Assert.assertEquals(baseUri, url);

        url = Utils.addDefaultProtocolToUrl(baseUriSecured);
        Assert.assertNotNull(url);
        Assert.assertEquals(baseUriSecured, url);
    }
}