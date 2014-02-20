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
package com.nebel_tv.content.services;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class VideoAssetsServiceTest {

	/**
	 *
	 */
	public VideoAssetsServiceTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of getVideoAssets request of service IvaWrapperWeb (remote server).
	 */
	@Test
	public void testServiceRequestRemote() {
		testServiceRequest("http://54.201.170.111:8080/IvaWrapperWeb/getVideoAssets?id=0");
	}

	/**
	 * Test of getVideoAssets request of service IvaWrapperWeb (local server).
	 */
	@Test
	public void testServiceRequestLocal() {
		testServiceRequest("http://127.0.0.1:8080/IvaWrapperWeb/getVideoAssets?id=0");
	}

	/**
	 * Test of getVideoAssets request of service IvaWrapperWeb
	 *
	 * @param url Service URL
	 */
	public void testServiceRequest(String url) {
		try {
			String response = IOUtils.toString(new URL(url));
			assertTrue(response != null && !response.isEmpty());

			JSONArray jsonVideoAssets = new JSONArray(response);
			assertFalse(jsonVideoAssets.length() == 0);

			for (int i = 0; i < jsonVideoAssets.length(); i++) {
				JSONObject jsonItem = jsonVideoAssets.getJSONObject(i);
				testJsonVideoAsset(jsonItem);
			}
		} catch (JSONException e) {
			fail("JSON parsing failed" + e.getMessage());
		} catch (IOException e) {
			fail("Web service request failed " + e.getMessage());
		}
	}

	/**
	 * Test of JSON presentation of the video asset The following keys are
	 * mandatory <code>rate, url</code>
	 *
	 * @param jsonAsset JSON object instance of the video asset
	 */
	private void testJsonVideoAsset(JSONObject asset) {
		assertNotNull(asset);

		assertTrue(asset.has("rate"));
		assertTrue(asset.has("URL"));
		assertTrue(asset.has("FileType"));
	}

	/**
	 * @param url
	 * @return
	 */
	public static String fixURL(String url) {
		StringBuilder fixedURL = new StringBuilder();
		String[] urlParts = url.split("\\?");
		fixedURL.append(urlParts[0]).append("?");

		if (urlParts.length > 1) {
			String[] pairs = urlParts[1].split("&");
			for (String pair : pairs) {
				String[] nameValuePair = pair.split("=");
				fixedURL.append("&")
						.append(nameValuePair[0])
						.append("=")
						.append(URLEncoder.encode(nameValuePair[1]));
			}
		}
		return fixedURL.toString();
	}
}
