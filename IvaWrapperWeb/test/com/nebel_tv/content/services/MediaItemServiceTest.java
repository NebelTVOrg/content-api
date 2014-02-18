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
package com.nebel_tv.content.services;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class MediaItemServiceTest {
       
    /**
     * 
     */
    public MediaItemServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of getMediaItem request of service IvaWrapperWeb (remote server).
     */
    @Test
    public void testServiceRequestRemote() {
        testServiceRequest("http://54.201.170.111:8080/IvaWrapperWeb/getMediaItem?id=2");
    }
    /**
     * Test of getMediaItem request of service IvaWrapperWeb (local server).
     */
    @Test
    public void testServiceRequestLocal() {
        testServiceRequest("http://127.0.0.1:8080/IvaWrapperWeb/getMediaItem?id=2");        
    }
    
    /**
     * Test of getMediaItem request of service IvaWrapperWeb (remote server).
     * @param url Service URL
     */
    public void testServiceRequest(String url) {    
        try {
            String response = IOUtils.toString(new URL(url));            
            assertTrue(response != null && !response.isEmpty());

            JSONObject jsonItem = new JSONObject(response);
            testJsonMediaItem(jsonItem);
        } catch (JSONException e) {
            fail("JSON parsing failed" + e.getMessage());
        } catch (IOException e) {
            fail("Web service request failed " + e.getMessage());
        }
    }

    /**
     * Test of JSON presentation of the media items
     * The following keys are mandatory <code>media_id, title, author, date</code>
     * 
     * @param item 
     */
    private void testJsonMediaItem(JSONObject item) {
        assertNotNull(item);
        
        assertTrue(item.has("Publishedid"));        
        assertTrue(item.has("DisplayTitle"))
                ;
        assertTrue(item.has("Poster"));
        assertTrue(item.has("Description"));
        assertTrue(item.has("Director"));     
        assertTrue(item.has("VideoAssets"));
    }
        
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
}