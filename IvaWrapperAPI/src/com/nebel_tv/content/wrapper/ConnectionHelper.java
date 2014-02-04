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
package com.nebel_tv.content.wrapper;

import java.net.URLEncoder;

/**
 *
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
}
