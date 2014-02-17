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

import java.util.HashMap;
import org.json.JSONArray;

/**
 *
 */
public class VideoAssetsCache {

    private static final HashMap<String, JSONArray> assetsById = new HashMap<String, JSONArray>();

    public static JSONArray getAssets(String id) {
        return assetsById.get(id);
    }

    public static JSONArray addAssets(String id, JSONArray item) {
        return assetsById.put(id, item);
    }
}
