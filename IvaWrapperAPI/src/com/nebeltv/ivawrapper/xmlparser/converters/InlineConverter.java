package com.nebeltv.ivawrapper.xmlparser.converters;

import com.nebeltv.ivawrapper.xmlparser.nodes.helpers.NodeNames;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 *
 * @author dst
 */
public class InlineConverter extends AbstractConverter {

	@Override
	public boolean canConvert(Class clazz) {
		return clazz.equals(String.class);
	}

	@Override
	public void marshal(Object value, HierarchicalStreamWriter writer,
					MarshallingContext context) {
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
					UnmarshallingContext context) {
		String value = "";

		while (reader.hasMoreChildren()) {
			reader.moveDown();
			final String nodeName = parseName(reader);
			if (NodeNames.ENTRY.equals(nodeName)) {
				while (reader.hasMoreChildren()) {
					reader.moveDown();
					final String inlineNodeName = parseName(reader);
					if (NodeNames.CONTENT.equals(inlineNodeName)) {

						while (reader.hasMoreChildren()) {
							reader.moveDown();
							final String contentNodeName = parseName(reader);
							if (NodeNames.PROPERTIES.equals(contentNodeName)) {

								while (reader.hasMoreChildren()) {
									reader.moveDown();
									final String propNodeName = parseName(reader);
									if (propNodeName != null) {
										boolean isPoster = propNodeName.startsWith(NodeNames.POSTER_URL);
										boolean isDescription = NodeNames.ITEM_DESCRIPTION.equals(propNodeName);
										boolean isDirector = NodeNames.FULL_NAME.equals(propNodeName);
										if (isPoster || isDescription || isDirector) {
											//System.out.println("<INLINE value found>: " + propNodeName);
											value = reader.getValue();
										}
									}
									reader.moveUp();
								}

							}
							reader.moveUp();
						}

					}
					reader.moveUp();
				}
			}
			reader.moveUp();
		}

		return value;
	}
}
