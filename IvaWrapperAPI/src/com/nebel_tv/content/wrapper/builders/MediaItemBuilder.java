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
package com.nebel_tv.content.wrapper.builders;


import com.nebel_tv.content.cache.MediaItemCache;
import com.nebel_tv.content.utils.ConnectionUtils;
import com.nebel_tv.content.wrapper.ConnectionHelper;

import org.json.JSONObject;

/**
 *
 */
public class MediaItemBuilder {

    public static final String MEDIA_ITEM_QUERY = "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms({publishedId})?"
            + "$expand=Poster,Description,Director&format=json";

    private final String queryUrl;

    private JSONObject item;
    private String json = "{}";
    private final String id;

    public MediaItemBuilder(String id) {
        this.queryUrl = MEDIA_ITEM_QUERY.replace("{publishedId}", id);
        this.id = id;
    }

    private void executeQuery() {
        try {
            String source = ConnectionUtils.getResponseAsString(ConnectionHelper.fixURL(queryUrl));
            JSONObject root = new JSONObject(source);
            item = (JSONObject) root.get("d");
        } catch (Exception ex) {
            System.out.println("ex: " + ex);
        }
    }
    
    public MediaItemBuilder build() {
        executeQuery();
        createMediaItem();
        generateJson();
        
        return this;
    }    

    private void generateJson() {
        if(item != null){
            json = item.toString();
        } 
    }
    
    public String get() {
        return this.json;
    }

    private void createMediaItem() {
        if (item != null) {
            MediaItemCache.addItem(id, item);
        }
    }
}
