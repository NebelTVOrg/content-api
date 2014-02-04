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

import com.nebel_tv.content.api.MediaWrapperResponse.ResponseResult;
import com.nebel_tv.content.api.MediaWrapperResponse.ResponseType;
import com.nebel_tv.content.api.core.WrapperMethodFactory;

/**
 *
 *
 */
public class MediaWrapper implements IMediaWrapper {

    /*
     * (non-Javadoc)
     * 
     * @see com.nebeltv.content.api.IMediaWrapper#getMedias(java.lang.Integer,
     * java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public MediaWrapperResponse getMediaData(String url) {

        try {
            String name = getUrlLastSegment(url);

            IWrapperMethod method = WrapperMethodFactory.getMethodByName(name);

            if (method != null) {
                List<NameValuePair> pairs = URLEncodedUtils.parse(
                        new URI(url), "UTF-8");

                Map<String, String> params = new HashMap<String, String>();
                for (NameValuePair param : pairs) {
                    params.put(param.getName(), param.getValue());
                }
                String data = method.execute(params);
                return new MediaWrapperResponse(data);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            return new MediaWrapperResponse(ResponseResult.InvalidParams, ResponseType.NA, "");
        }
        return new MediaWrapperResponse(ResponseResult.InvalidUrl, ResponseType.NA, "");
    }

    /**
     *
     * @param url
     */
    public static String getUrlLastSegment(final String url) {
        return url.replaceFirst(".*/([^/?]+).*", "$1");
    }

    /**
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MediaWrapper wrapper = new MediaWrapper();
        MediaWrapperResponse response = wrapper.getMediaData("http://54.201.170.111:8080/IvaWrapperWeb/getMedias?skip=100&n=3&category=0");
        System.out.println(response.responseData);
    }
}
