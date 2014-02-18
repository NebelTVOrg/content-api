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

import org.apache.commons.lang3.StringUtils;

import com.nebel_tv.content.utils.ConnectionUtils;
import com.nebel_tv.content.wrapper.ConnectionHelper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 */
public class MediasBuilder  {

    public static final String MEDIAS_QUERY_PART_1 = "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms()?$skip={skip}&$top={top}&";
    public static final String MEDIAS_QUERY_PART_2 = "$filter=MediaId eq {MediaId}&";
    public static final String MEDIAS_QUERY_PART_3 = "$expand=Poster,Description,Director,VideoAssetScreenCapture&format=json";
    
    private String queryUrl;
    private JSONArray items;       
    
    private String json = "[]";

    public MediasBuilder(Integer n, Integer skip, String category, String viewType, String viewTypePeriod) {
        this.queryUrl = MEDIAS_QUERY_PART_1;
        if (n == null || n < 1) {
            n = 5;
        }
        if (skip == null || skip < 0) {
            skip = 0;
        }
        this.queryUrl = this.queryUrl.replace("{top}", n.toString());
        this.queryUrl = this.queryUrl.replace("{skip}", skip.toString());
        if (StringUtils.isNotBlank(category)) {
            this.queryUrl += MEDIAS_QUERY_PART_2.replace("{MediaId}", "" + category);
        }
        this.queryUrl += MEDIAS_QUERY_PART_3;
    }

    private void executeQuery() {
        try {
            String source = ConnectionUtils.getResponseAsString(ConnectionHelper.fixURL(queryUrl));
            JSONObject root = new JSONObject(source);
            items = (JSONArray) root.get("d");
        } catch (Exception ex) {
            Logger.getLogger(MediasBuilder.class.getName()).log(Level.WARNING, null, ex);
        }
    }

    private void generateJson() {
        if(items != null){
            json = items.toString();
        } 
    }

    public MediasBuilder build() {
        executeQuery();
        createMediaItems();
        generateJson();
        return this;
    }

    public String get() {
        return this.json;
    }

    private void createMediaItems() {
        if(items == null)
            return;
        
        for (int i = 0; i < items.length(); i++) {
            try {
                JSONObject item = items.getJSONObject(i);
                if (item != null) {
                    int id = item.getInt("Publishedid");
                    MediaItemCache.addItem(String.valueOf(id) , item);                    
                }                        
            } catch (JSONException ex) {
                Logger.getLogger(MediasBuilder.class.getName()).log(Level.WARNING, null, ex);
            }
        }
    }
}
