/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nebeltv.ivawrapper.xmlparser.nodes;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

/**
 *
 * @author dst
 */
@XStreamAlias("content")
public class Content {

	@XStreamAlias("m:properties")
	private Properties properties;

	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return reflectionToString(this, SHORT_PREFIX_STYLE);
	}
}
