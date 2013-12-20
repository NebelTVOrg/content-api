/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nebeltv.ivawrapper.xmlparser.nodes;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;
import java.util.List;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

/**
 *
 * @author dst
 */
@XStreamAlias("feed")
public class Feed {

	@XStreamImplicit(itemFieldName = "link")
	private List<String> links;
	@XStreamImplicit(itemFieldName = "entry")
	private List<Entry> entries = new ArrayList<Entry>();

	public void addEntry(Entry entry) {
		entries.add(entry);
	}

	/**
	 * @return the link
	 */
	public List<String> getLinks() {
		return links;
	}

	/**
	 * @param links the link to set
	 */
	public void setLinks(List<String> links) {
		this.links = links;
	}

	/**
	 * @return the entries
	 */
	public List<Entry> getEntries() {
		return entries;
	}

	/**
	 * @param entries the entries to set
	 */
	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

	@Override
	public String toString() {
		return reflectionToString(this, SHORT_PREFIX_STYLE);
	}
}
