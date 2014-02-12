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

import com.nebel_tv.content.wrapper.builders.MediasBuilder;
import com.nebel_tv.content.wrapper.builders.MediaItemBuilder;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class LiveWrapper implements IWrapper {
    
    private final static Map<Integer, String> videoAssets = new HashMap<Integer, String>();    
    
    static {        
        String asset0= "{"
                + "\"url\":\"http://54.201.170.111/assets/001-180p-185kb.mp4\","
                + "\"rate\":\"185\""
                + "}";
        
        String asset1= "{"
                + "\"url\":\"http://54.201.170.111/assets/001-270p-686kb.mp4\","
                + "\"rate\":\"686\""
                + "}";
        
        String asset2= "{"
                + "\"url\":\"http://54.201.170.111/assets/001-720p-2500kb.mp4\","
                + "\"rate\":\"2500\""
                + "}";
        
        videoAssets.put(0, '['
                + asset0 + ","
                + asset1 + ","
                + asset2
                + "]");
    }

    /**
     * 
     */
    public LiveWrapper() {
    }

    @Override
    public String getMedias(Integer n, Integer skip, String category, String viewType, String viewTypePeriod) {
        return new MediasBuilder(n, skip, category, viewType, viewTypePeriod).build().get();
    }

    @Override
    public String getMediaItem(Integer id) {
        if (id != null) {
            return new MediaItemBuilder(id.toString()).build().get();
        }
        return "-1";
    }

    @Override
    public String getVideoAssets(Integer id) {
        return videoAssets.get(0);
    }
}
