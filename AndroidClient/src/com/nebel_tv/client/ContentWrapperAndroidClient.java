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
package com.nebel_tv.client;

import com.nebel_tv.content.wrapper.Wrapper;
import com.nebel_tv.content.wrapper.WrapperTypes;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Android client emulation
 */
public class ContentWrapperAndroidClient {

    /**
     *
     */
    private static final String MEDIA_ITEM_SERVICE = "http://54.201.170.111:8080/IvaWrapperWeb/getMediaItem?n=";

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println(getMediaItem(2));
        System.out.println(getMediaItemUsingWebService(2));        
        
        //System.out.println(getMedias(2, 5, "0", null, null));
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

    public static String getMediaItem(Integer id) throws Exception {
        return Wrapper.getWrapper(WrapperTypes.LIVE).getMediaItem(id);
    }

    public static String getMedias(Integer n, Integer skip, String category, String viewType, String viewTypePeriod) throws Exception {
        return Wrapper.getWrapper(WrapperTypes.LIVE).getMedias(n, skip, category, viewType, viewTypePeriod);
    }

    @Deprecated
    public static String getString(String url) throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet getRequest = new HttpGet(fixURL(url));

        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        return httpclient.execute(getRequest, responseHandler);
    }

    @Deprecated
    public static String getMediaItemUsingWebService(Integer id) {
        if (id != null) {
            try {
                String item = getString(MEDIA_ITEM_SERVICE + id.toString());
                System.out.println("item: " + item);
                return item;
            } catch (Exception ex) {
                Logger.getLogger(ContentWrapperAndroidClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
