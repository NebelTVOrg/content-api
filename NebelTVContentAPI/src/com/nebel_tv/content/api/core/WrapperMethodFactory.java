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
package com.nebel_tv.content.api.core;

import com.nebel_tv.content.api.IWrapperMethod;

/**
 *
 */
public class WrapperMethodFactory {

	/**
	 *
	 * @param name The name of wrapper method (Web Service interface)
	 * @return
	 */
	public static IWrapperMethod getMethodByName(String name) {
		if (name == null || name.isEmpty()) {
			return null;
		}

		if (name.equals(GetMediaItemMethod.getName())) {
			return new GetMediaItemMethod();
		} else if (name.equals(GetMediasMethod.getName())) {
			return new GetMediasMethod();
		} else if (name.equals(GetVideoAssetsMethod.getName())) {
			return new GetVideoAssetsMethod();
		}
		return null;
	}
}
