/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nebeltv.ivawrapper.xmlparser.nodes;

import com.nebeltv.ivawrapper.xmlparser.converters.InlineConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

/**
 *
 * @author dst
 */
@XStreamAlias("link")
public class Link {

	@XStreamAlias("title")
	@XStreamAsAttribute
	private String title;

	@XStreamAlias("m:inline")
	@XStreamConverter(InlineConverter.class)
	private String value;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the inline
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the inline to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return reflectionToString(this, SHORT_PREFIX_STYLE);
	}
}
