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
public class EntryConverter implements Converter {

    public boolean canConvert(Class clazz) {
        return clazz.equals(Entry.class);
    }

    public void marshal(Object value, HierarchicalStreamWriter writer,
            MarshallingContext context) {
    }

    public Object unmarshal(HierarchicalStreamReader reader,
            UnmarshallingContext context) {
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            if ("published".equals(reader.getNodeName())) {
                try {
                    Entry entry = new Entry();
                    entry.publishedId = reader.getValue();
                    return entry;
                } catch (Exception ex) {
                    System.out.println("ex: " + ex);
                }
            }
            reader.moveUp();
        }
        return null;
    }

}
