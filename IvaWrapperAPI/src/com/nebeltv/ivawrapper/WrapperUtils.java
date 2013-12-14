package com.nebeltv.ivawrapper;

/**
 *
 * @author dst
 */
public class WrapperUtils {

	/**
	 * safe String to Integer converter
	 *
	 * @param number string to parse
	 * @return parsed Integer or null
	 */
	public static Integer getInt(final String number) {
		try {
			return Integer.parseInt(number);
		} catch (Exception e) {
			System.out.println("e: " + e);
		}
		return null;
	}
}
