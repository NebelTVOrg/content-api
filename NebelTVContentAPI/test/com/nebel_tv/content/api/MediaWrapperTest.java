/**
 * Copyright (C) 2014 Nebel TV (http://nebel.tv)
    
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
    
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
    
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.nebel_tv.content.api;

import java.io.IOException;
import java.net.URLEncoder;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
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
public class MediaWrapperTest {
    
    /**
     * 
     */
    public MediaWrapperTest() {
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
     * Test of getMediaData (<code>getMedias</code>) method, of class MediaWrapper.
     */
    @Test
    public void testGetMediaDataGetMedias() {
        final String url = "http://nebel.tv/IvaWrapperWeb/getMedias?skip=100&n=9&category=0";
        
        MediaWrapper instance = new MediaWrapper();
        MediaWrapperResponse result = instance.getMediaData(url);
        assertTrue(result.responseType == MediaWrapperResponse.ResponseType.NA);       
        assertTrue(result.responseResult == MediaWrapperResponse.ResponseResult.Ok);
        
        try {
            JSONArray jsonGetMedias = new JSONArray(result.responseData);
            assertFalse(jsonGetMedias.length() == 0);
            
            for (int i = 0; i < jsonGetMedias.length(); i++) {
                JSONObject jsonItem = jsonGetMedias.getJSONObject(i);
                testJsonMediaItem(jsonItem);
            }
        } catch (JSONException e) {
            fail("JSON parsing failed" + e.getMessage());
        }
    }
    
    /**
     * Test (compare) of getMediaData (<code>getMedias</code>) results returned by Web Service and Wrapper Library.
     */
    @Test
    public void testGetMediasCompare() {
        final String url = "http://127.0.0.1:8080/IvaWrapperWeb/getMedias?skip=100&n=9&category=0";
        
        try {
            MediaWrapper instance = new MediaWrapper();
            MediaWrapperResponse result = instance.getMediaData(url);

            HttpGet getRequest = new HttpGet(fixURL(url));        
            HttpClient httpclient = new DefaultHttpClient();
            
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String response = httpclient.execute(getRequest, responseHandler);
            
            assertTrue(result.responseData.equals(response));
        } catch (IOException e) {
            fail("Web service request failed " + e.getMessage());
        }
    }    
    
    /**
     * Test of getMediaData (<code>getMediaItem</code>) method, of class MediaWrapper.
     */
    @Test
    public void testGetMediaDataGetMediaItem() {
        final String url = "http://54.201.170.111:8080/IvaWrapperWeb/getMediaItem?n=2";
        
        MediaWrapper instance = new MediaWrapper();
        MediaWrapperResponse result = instance.getMediaData(url);
        assertTrue(result.responseType == MediaWrapperResponse.ResponseType.NA);
        assertTrue(result.responseResult == MediaWrapperResponse.ResponseResult.Ok);
        
        try {
            JSONObject jsonItem = new JSONObject(result.responseData);
            testJsonMediaItem(jsonItem);
        } catch (JSONException e) {
            fail("JSON parsing failed" + e.getMessage());
        }        
    }
    
    /**
     * Test (compare) of getMediaData (<code>getMediaItem</code>) results returned by Web Service and Wrapper Library.
     */
    @Test
    public void testGetMediaItemCompare() {
        final String url = "http://127.0.0.1:8080/IvaWrapperWeb/getMediaItem?n=2";
        try {
            MediaWrapper instance = new MediaWrapper();
            MediaWrapperResponse result = instance.getMediaData(url);
            
            HttpGet getRequest = new HttpGet(fixURL(url));        
            HttpClient httpclient = new DefaultHttpClient();
            
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String response = httpclient.execute(getRequest, responseHandler);
            
            assertTrue(result.responseData.equals(response));
        } catch (IOException e) {
            fail("Web service request failed " + e.getMessage());
        }
    }    
    
    /**
     * Test of getMediaData (<code>getMedias</code>) method, of class MediaWrapper.
     */
    @Test
    public void testGetMediaDataGetVideoAssets() {
        final String url = "http://54.201.170.111:8080/IvaWrapperWeb/getVideoAssets?id=0";
        
        MediaWrapper instance = new MediaWrapper();
        MediaWrapperResponse result = instance.getMediaData(url);
        assertTrue(result.responseType == MediaWrapperResponse.ResponseType.VideoAssets);       
        assertTrue(result.responseResult == MediaWrapperResponse.ResponseResult.Ok);
        
        try {
            JSONArray jsonVideoAssets = new JSONArray(result.responseData);
            assertFalse(jsonVideoAssets.length() == 0);
            
            for (int i = 0; i < jsonVideoAssets.length(); i++) {
                JSONObject jsonAsset = jsonVideoAssets.getJSONObject(i);
                testJsonVideoAsset(jsonAsset);
            }
        } catch (JSONException e) {
            fail("JSON parsing failed" + e.getMessage());
        }
    }
    
    /**
     * Test of getUrlLastSegment (<code>getMedias</code>) method, of class MediaWrapper.
     */
    @Test
    public void testGetUrlLastSegmentGetMedias() {
        String urlGetMedias = "http://54.201.170.111:8080/IvaWrapperWeb/getMedias?skip=100&n=3&category=0";
        String result = MediaWrapper.getUrlLastSegment(urlGetMedias);
        assertEquals(result, "getMedias");
        
        urlGetMedias = "http://google.com/IvaWrapperWeb/getMedias?skip=100&n=3&category=0";
        result = MediaWrapper.getUrlLastSegment(urlGetMedias);
        assertEquals(result, "getMedias");        
    }
    
    /**
     * Test of getUrlLastSegment (<code>getMediaItem</code>) method, of class MediaWrapper.
     */
    @Test
    public void testGetUrlLastSegmentGetMediaItem() {        
        String urlGetMediaItem = "http://54.201.170.111:8080/IvaWrapperWeb/getMediaItem?n=2";
        String result = MediaWrapper.getUrlLastSegment(urlGetMediaItem);
        assertEquals(result, "getMediaItem");
        
        urlGetMediaItem = "http://pravda.com.ua/IvaWrapperWeb/getMediaItem?n=2";
        result = MediaWrapper.getUrlLastSegment(urlGetMediaItem);
        assertEquals(result, "getMediaItem");                
    }

    /**
     * Test of getUrlLastSegment (<code>getVideoAssets</code>) method, of class MediaWrapper.
     */
    @Test
    public void testGetUrlLastSegmentGetVideoAssets() {
        String urlGetVideoAssets = "http://54.201.170.111:8080/IvaWrapperWeb/getVideoAssets?id=0";
        String result = MediaWrapper.getUrlLastSegment(urlGetVideoAssets);
        assertEquals(result, "getVideoAssets");
        
        urlGetVideoAssets = "http://google.com/IvaWrapperWeb/getVideoAssets?id=0";
        result = MediaWrapper.getUrlLastSegment(urlGetVideoAssets);
        assertEquals(result, "getVideoAssets");        
    }
    
    /**
     * Test of JSON presentation of the media item
     * The following keys are mandatory <code>media_id, title, author, date</code>
     */
    private void testJsonMediaItem(JSONObject jsonItem) {
        assertNotNull(jsonItem);
        
        System.out.println(jsonItem);
        
        assertTrue(jsonItem.has("media_id"));
        assertTrue(jsonItem.has("title"));
        assertTrue(jsonItem.has("author"));
        assertTrue(jsonItem.has("date"));        
    }
    
    /**
     * Test of JSON presentation of the video asset
     * The following keys are mandatory <code>rate, url</code>
     * 
     * @param jsonAsset JSON object instance of the video asset
     */
    private void testJsonVideoAsset(JSONObject jsonAsset) {
        assertNotNull(jsonAsset);
        
        assertTrue(jsonAsset.has("rate"));
        assertTrue(jsonAsset.has("url"));       
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