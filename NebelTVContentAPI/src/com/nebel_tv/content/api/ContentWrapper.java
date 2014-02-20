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

import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import com.nebel_tv.content.api.WrapperResponse.ResponseResult;
import com.nebel_tv.content.api.WrapperResponse.ResponseType;
import com.nebel_tv.content.api.core.WrapperMethodFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class ContentWrapper implements IContentWrapper {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nebeltv.content.api.IMediaWrapper#getMedias(java.lang.Integer,
	 * java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public WrapperResponse getMediaData(String url) {

		try {
			String name = getUrlLastSegment(url);
			IWrapperMethod method = WrapperMethodFactory.getMethodByName(name);

			if (method != null) {
				List<NameValuePair> pairs = URLEncodedUtils.parse(new URI(url), "UTF-8");

				Map<String, String> params = new HashMap<String, String>();
				for (NameValuePair param : pairs) {
					params.put(param.getName(), param.getValue());
				}
				return method.execute(params);
			}
		} catch (URISyntaxException ex) {
			Logger.getLogger(ContentWrapper.class.getName()).log(Level.WARNING, null, ex);
		} catch (InvalidParameterException ex) {
			Logger.getLogger(ContentWrapper.class.getName()).log(Level.SEVERE, null, ex);

			return new WrapperResponse(ResponseResult.InvalidParams, ResponseType.Content, "");
		}
		return new WrapperResponse(ResponseResult.InvalidUrl, ResponseType.Content, "");
	}

	/**
	 *
	 * @param url
	 */
	public static String getUrlLastSegment(final String url) {
		return url.replaceFirst(".*/([^/?]+).*", "$1");
	}
}
