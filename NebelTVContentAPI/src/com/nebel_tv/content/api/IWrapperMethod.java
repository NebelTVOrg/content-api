package com.nebel_tv.content.api;

import java.security.InvalidParameterException;
import java.util.Map;

public interface IWrapperMethod {

	/**
	 * 
	 * @param params
	 * @return
	 * @throws InvalidParameterException
	 */
	abstract String execute(Map<String, String> params)
			throws InvalidParameterException;
}
