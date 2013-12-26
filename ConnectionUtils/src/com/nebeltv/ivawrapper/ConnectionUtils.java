/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nebeltv.ivawrapper;

import java.io.InputStream;

import org.apache.http.client.fluent.Request;

/**
 *
 * @author dst
 */
public class ConnectionUtils {

    public static InputStream getStream(String url) throws Exception {
        return Request.Get(url).addHeader("Developerid", "B43BF933-5CB5-434A-B0A8-717FC149FBED").execute().returnContent().asStream();
    }
}
