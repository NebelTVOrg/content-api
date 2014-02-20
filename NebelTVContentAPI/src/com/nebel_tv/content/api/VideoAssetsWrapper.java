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
package com.nebel_tv.content.api;

import com.nebel_tv.content.api.entitis.VideoAsset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 */
public class VideoAssetsWrapper {

	private List<VideoAsset> assets = new ArrayList<VideoAsset>();

	/**
	 *
	 * @param response
	 */
	public VideoAssetsWrapper(String response) {
		try {
			JSONArray array = new JSONArray(response);
			for (int i = 0; i < array.length(); i++) {
				JSONObject asset = array.getJSONObject(i);
				assets.add(new VideoAsset(asset.getString("URL"), asset.getString("rate")));
			}
		} catch (JSONException ex) {
			Logger.getLogger(VideoAssetsWrapper.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 *
	 * @return List of video URLs
	 */
	public String[] getVideoURLs() {

		List<String> arr = new ArrayList<String>();
		for (VideoAsset asset : assets) {
			arr.add(asset.getUrl());
		}
		return arr.toArray(new String[arr.size()]);
	}
}
