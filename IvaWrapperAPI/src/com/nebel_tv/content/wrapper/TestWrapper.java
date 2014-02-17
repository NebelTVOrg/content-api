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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 */
class TestWrapper implements IWrapper {

    private static TestWrapper instance = null;
    
    private final static Map<String, String> mediasData = new HashMap<String, String>();
    private final static Map<Integer, String> mediaItems = new HashMap<Integer, String>();

    private final static Map<Integer, String> videoAssets = new HashMap<Integer, String>();    
    
    static {

    }

    private TestWrapper()  {
 
    }
    
    private void init(){
       try {
            String response = IOUtils.toString( this.getClass().getResourceAsStream("resources/getVideoAssets_encodes.json"), "UTF-8");           
            
            JSONObject root = new JSONObject(response);
            JSONObject item = (JSONObject) root.get("d");                        
            mediaItems.put(new Integer(749049), item.toString());
            
        } catch (IOException ex) {
            Logger.getLogger(TestWrapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(TestWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static TestWrapper getInstance() {
        if(instance == null){
             instance = new TestWrapper();
             instance.init();
        }
        return instance;
    }    

    @Override
    public String getMedias(Integer n, Integer skip, String category, String viewType, String viewTypePeriod) {
        return instance.mediasData.get(category);
    }

    @Override
    public String getMediaItem(Integer id) {
        return instance.mediaItems.get(id);
    }

    @Override
    public String getVideoAssets(Integer id) {
        return instance.videoAssets.get(id);
    }
}