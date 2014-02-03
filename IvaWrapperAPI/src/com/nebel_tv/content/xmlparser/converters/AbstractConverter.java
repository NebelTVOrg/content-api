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
package com.nebel_tv.content.xmlparser.converters;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;

/**
 *
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
