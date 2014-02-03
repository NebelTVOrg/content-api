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
package com.nebel_tv.content.cache;

import com.nebel_tv.content.wrapper.entities.MediaItem;
import java.util.HashMap;

/**
 *
 */
public class MediaItemCache {

    private static final HashMap<String, MediaItem> itemById = new HashMap<String, MediaItem>();

    public static MediaItem getItem(String id) {
        return itemById.get(id);
    }

    public static MediaItem addItem(MediaItem item) {
        String id = item.getMediaId();
        return itemById.put(id, item);
    }
}
