/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nebeltv.ivawrapper;

import com.thoughtworks.xstream.io.xml.XppReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

/**
 *
 * @author dst
 */
public class ConnectionHelper {

    /**
     * @param url
     * @return
     */
    public static String fixURL(String url) {
        StringBuilder fixedURL = new StringBuilder();
        String[] urlParts = url.split("\\?");
        fixedURL.append(urlParts[0]).append("?");

        if (urlParts.length > 1) {
            String[] pairs = urlParts[1].split("&");
            for (String pair : pairs) {
                String[] nameValuePair = pair.split("=");
                fixedURL.append("&")
                        .append(nameValuePair[0])
                        .append("=")
                        .append(URLEncoder.encode(nameValuePair[1]));
            }
        }
        return fixedURL.toString();
    }

    /*public static InputStream getStream(String url) throws Exception {
     return Request.Get(fixURL(url)).addHeader("Developerid", "B43BF933-5CB5-434A-B0A8-717FC149FBED").execute().returnContent().asStream();
     }*/
    public static InputStream getStream(String url) throws Exception {
        return ConnectionUtils.getStream(fixURL(url));
    }

    public static XppReader getXppStreamReader(String url) throws Exception {
        final InputStream stream = getStream(url);
        InputStreamReader inputStreamReader = new InputStreamReader(stream);
        XppReader reader = new XppReader(inputStreamReader);
        return reader;
    }
}
