/**
    Copyright (C) 2014 Nebel TV (http://nebel.tv)
    
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.nebel_tv.content.api.core;

import java.security.InvalidParameterException;
import java.util.Map;

import com.nebel_tv.content.wrapper.WrapperUtils;

/**
 * 
 * 
 */
public class GetMediasMethod extends WrapperMethod {

	/**
	 * 
	 * @return
	 */
	public static String getName() {
		return "getMedias";
	}

	/**
	 * 
	 */
	@Override
	public String execute(Map<String, String> params) throws InvalidParameterException {
		if(params == null || params.size() < 3)
			throw new InvalidParameterException();
		
		final String n = params.get("n");
		final String skip = params.get("skip");
		final String category = params.get("category");
		final String viewType = params.get("viewType");
		final String viewTypePeriod = params.get("viewTypePeriod");
		
		Integer nParsed = WrapperUtils.getInt(n);
		Integer skipParsed = WrapperUtils.getInt(skip);
		
		return wrapper.getMedias(nParsed, skipParsed, category, viewType, viewTypePeriod);
	}
}
