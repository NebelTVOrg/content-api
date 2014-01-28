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
package com.nebel_tv.content.wrapper;

import com.nebel_tv.content.utils.ConnectionUtils;
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
