package com.nebeltv.ivawrapper.xmlparser.converters;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;

/**
 *
 * @author dst
 */
public abstract class AbstractConverter implements Converter {

	public static final String NODE_NAME_SPLITTER = ":";

	public String parseName(HierarchicalStreamReader reader) {
		String nodeName = reader.getNodeName();
		if (nodeName != null && nodeName.contains(NODE_NAME_SPLITTER)) {
			nodeName = nodeName.split(NODE_NAME_SPLITTER)[1].toLowerCase();
		}
		return nodeName;
	}
}
