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

import com.nebel_tv.content.cache.VideoAssetsCache;
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
public class VideoAssetsBuilder {

	public static final String VIDEO_ASSETS_QUERY = "http://api.internetvideoarchive.com/1.0/DataService/VideoAssets({publishedId})?$expand=Encodes&format=json";

	private final String queryUrl;

	private JSONArray assets;
	private String json = "[]";
	private final String id;

	public VideoAssetsBuilder(String id) {
		this.queryUrl = VIDEO_ASSETS_QUERY.replace("{publishedId}", id);
		this.id = id;
	}

	private void executeQuery() {
		assets = VideoAssetsCache.getAssets(id);

		if (assets == null) {
			try {
				String source = ConnectionUtils.getResponseAsString(ConnectionHelper.fixURL(queryUrl));

				JSONObject root = (JSONObject) (new JSONObject(source)).get("d");
				JSONObject encodes = (JSONObject) root.get("Encodes");

				assets = (JSONArray) encodes.get("results");
				fixVidoURL();
			} catch (Exception ex) {
				Logger.getLogger(VideoAssetsBuilder.class.getName()).log(Level.WARNING, null, ex);
			}
		}
	}

	public VideoAssetsBuilder build() {
		executeQuery();
		createVideoAsses();
		generateJson();

		return this;
	}

	private void generateJson() {
		if (assets != null) {
			json = assets.toString();
		}
	}

	public String get() {
		return this.json;
	}

	private void createVideoAsses() {
		if (assets != null) {
			VideoAssetsCache.addAssets(id, assets);
		}
	}

	/**
	 *
	 */
	private void fixVidoURL() {
		if (assets == null) {
			return;
		}

		for (int i = 0; i < assets.length(); i++) {
			try {
				JSONObject item = assets.getJSONObject(i);
				if (item != null) {
					int rate = item.getInt("rate");
					switch (rate) {
						case 80:
						case 212:
						case 185:
							item.put("URL", "http://54.201.170.111/assets/001-180p-185kb.mp4");
							break;
						case 450:
						case 600:
						case 686:
						case 750:
							item.put("URL", "http://54.201.170.111/assets/001-270p-686kb.mp4");
							break;
						case 1500:
						case 2500:
						case 8000:
						default:
							item.put("URL", "http://54.201.170.111/assets/001-720p-2500kb.mp4");
							break;
					}
				}
			} catch (JSONException ex) {
				Logger.getLogger(ConnectionUtils.class.getName()).log(Level.WARNING, null, ex);
			}
		}
	}
}
