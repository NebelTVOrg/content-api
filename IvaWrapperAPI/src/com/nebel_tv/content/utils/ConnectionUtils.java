/**
 * Copyright (C) 2014 Nebel TV (http://nebel.tv)
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.nebel_tv.content.utils;

import java.util.Stack;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 */
public class ConnectionUtils {

    /**
     * Stack of developer IDs
     */
    private static Stack<String> ids = new Stack<String>();

    /**
     *
     */
    private static void init() {
        ids.push("B43BF933-5CB5-434A-B0A8-717FC149FBED");
        ids.push("2A702798-6DBA-417D-A8BC-175CAEFFD2D6");
    }

    /**
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static InputStream getResponseAsStream(String url) throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet getRequest = new HttpGet(url);

        if (ids.empty()) {
            init();
        }
        String developerId = ids.pop();
        getRequest.addHeader("Developerid", developerId);
        try {
            // Execute HTTP Post Request
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = httpclient.execute(getRequest, responseHandler);

            ids.push(developerId);
            return new ByteArrayInputStream(responseBody.getBytes());

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String getResponseAsString(String url) throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet getRequest = new HttpGet(url);

        if (ids.empty()) {
            init();
        }
        String developerId = ids.pop();
        getRequest.addHeader("Developerid", developerId);
        try {
            // Execute HTTP Post Request
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = httpclient.execute(getRequest, responseHandler);

            ids.push(developerId);
            return responseBody;

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}