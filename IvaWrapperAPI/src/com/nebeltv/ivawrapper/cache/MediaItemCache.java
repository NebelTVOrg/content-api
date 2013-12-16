/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nebeltv.ivawrapper.cache;

import com.nebeltv.ivawrapper.MediaItem;
import java.util.HashMap;

/**
 *
 * @author dtv
 */
public class MediaItemCache {

	private static final HashMap<String, MediaItem> itemById = new HashMap<>();

	public static MediaItem getItem(String id) {
		return itemById.get(id);
	}

	public static MediaItem addItem(MediaItem item) {
		String id = item.getMediaId();
		return itemById.put(id, item);
	}
}
