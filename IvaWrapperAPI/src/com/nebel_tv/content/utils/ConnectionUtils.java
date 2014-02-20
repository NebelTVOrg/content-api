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
package com.nebel_tv.content.utils;

import java.util.Stack;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;

/**
 *
 */
public class ConnectionUtils {

	/**
	 * Stack of developer IDs
	 */
	private static final Stack<String> ids = new Stack<String>();

	/**
	 *
	 */
	private static void init() {
		ids.push("B43BF933-5CB5-434A-B0A8-717FC149FBED");
		ids.push("2A702798-6DBA-417D-A8BC-175CAEFFD2D6");
	}

	/**
	 *
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static InputStream getResponseAsStream(String url) throws Exception {
		if (ids.empty()) {
			init();
		}
		String developerId = ids.pop();
		url = url + "&Developerid=" + developerId;
		try {
			String result = IOUtils.toString(new URL(url));

			ids.push(developerId);
			return new ByteArrayInputStream(result.getBytes());
		} catch (MalformedURLException ex) {
			Logger.getLogger(ConnectionUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ConnectionUtils.class.getName()).log(Level.WARNING, null, ex);
		}
		return null;
	}

	/**
	 *
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String getResponseAsString(String url) throws Exception {
		if (ids.empty()) {
			init();
		}
		String developerId = ids.pop();

		try {
			url = url + "&Developerid=" + developerId;
			String result = IOUtils.toString(new URL(url));

			ids.push(developerId);
			return result;
		} catch (MalformedURLException ex) {
			Logger.getLogger(ConnectionUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ConnectionUtils.class.getName()).log(Level.WARNING, null, ex);
		}
		return null;
	}
}
