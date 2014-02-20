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

/**
 *
 */
public class WrapperResponse {

	/**
	 *
	 */
	public enum ResponseType {

		Content, VideoAssets, MediaData
	}

	/**
	 *
	 */
	public enum ResponseResult {

		Ok, InvalidUrl, InvalidParams, ServiceError, ConnectionTimeout
	}

	/**
	 *
	 */
	public String responseData;

	/**
	 *
	 */
	public ResponseType responseType = ResponseType.Content;

	/**
	 *
	 */
	public ResponseResult responseResult = ResponseResult.Ok;

	/**
	 *
	 * @param data
	 */
	public WrapperResponse(String data) {
		responseData = data;
	}

	/**
	 *
	 * @param result
	 * @param type
	 * @param data
	 */
	public WrapperResponse(ResponseResult result, ResponseType type, String data) {
		responseResult = result;
		responseType = type;

		responseData = data;
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MediaWrapperResponse: [responseResult=");
		builder.append(responseResult);
		builder.append(", responseType=");
		builder.append(responseType);
		builder.append(", responseData=");
		builder.append(responseData);
		builder.append("]");

		return builder.toString();
	}
}
