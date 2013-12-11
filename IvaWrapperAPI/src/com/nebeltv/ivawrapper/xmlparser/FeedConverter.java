/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nebeltv.ivawrapper.xmlparser;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 *
 * @author dmitry
 */
public class FeedConverter implements Converter {

    public boolean canConvert(Class clazz) {
        return clazz.equals(Feed.class);
    }

    public void marshal(Object value, HierarchicalStreamWriter writer,
            MarshallingContext context) {
    }

    public Object unmarshal(HierarchicalStreamReader reader,
            UnmarshallingContext context) {
        Feed feed = new Feed();
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            if ("id".equals(reader.getNodeName())) {
                feed.id = reader.getValue();
            }
            if ("entry".equals(reader.getNodeName())) {
                try {
                    Entry entry = (Entry) context.convertAnother(this, Entry.class);
                    feed.addEntry(entry);
                } catch (Exception ex) {
                    System.out.println("ex: " + ex);
                }
            }
            reader.moveUp();
        }
        return null;
    }

}
