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
package com.nebel_tv.content.wrapper.entities;

/**
 * 
 *
 */
public class VideoAsset {
    private String rate;
    private String url;
    
    /**
     * @return the rate
     */
    public String getRate() {
        return rate;
    }
    
    /**
     * @param value The video rate to set
     */
    public void setRate(String value) {
        this.rate = value;
    }    

    /**
     * @return The video remote URL
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * @param value The video URL to set
     */
    public void setURL(String value) {
        this.url = value;
    }    
}
