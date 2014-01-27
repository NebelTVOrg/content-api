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

import com.nebel_tv.content.wrapper.builders.MediasBuilder;
import com.nebel_tv.content.wrapper.builders.MediaItemBuilder;

/**
 *
 */
public class LiveWrapper implements IWrapper {

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
}
