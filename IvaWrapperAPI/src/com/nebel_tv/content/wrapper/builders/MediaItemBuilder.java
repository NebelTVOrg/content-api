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
package com.nebel_tv.content.wrapper.builders;

import com.nebel_tv.content.wrapper.ConnectionHelper;
import com.nebel_tv.content.wrapper.entities.MediaItem;
import com.nebel_tv.content.cache.MediaItemCache;
import com.nebel_tv.content.xmlparser.nodes.Entry;
import com.nebel_tv.content.xmlparser.nodes.Feed;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.io.xml.XppReader;
import java.io.Writer;

/**
 *
 * @author dst
 */
public class MediaItemBuilder extends MediaItemConverter {

    public static final String MEDIA_ITEM_QUERY = "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms({publishedId})?"
            + "$expand=Poster,Description,Director";

    private final String queryUrl;
    private Entry originalEntry;
    private MediaItem item;
    private String json = "-1";
    private final String id;

    public MediaItemBuilder(String id) {
        this.queryUrl = MEDIA_ITEM_QUERY.replace("{publishedId}", id);
        this.id = id;
    }

    private void executeQuery() {
        try {
            XStream xStream = new XStream(new XppDriver());
            xStream.alias("entry", Entry.class);
            xStream.alias("feed", Feed.class);
            xStream.autodetectAnnotations(true);
            xStream.ignoreUnknownElements();

            XppReader reader = ConnectionHelper.getXppStreamReader(queryUrl);

            Feed feed;
            // should be Entry, but lets check first
            Object result = xStream.unmarshal(reader);
            if (result instanceof Feed) {
                feed = (Feed) result;
            } else if (result instanceof Entry) {
                feed = new Feed();
                feed.addEntry((Entry) result);
            } else {
                feed = new Feed();
            }

            //System.out.println(feed.toString());
            if (feed.getEntries() != null && !feed.getEntries().isEmpty()) {
                originalEntry = feed.getEntries().iterator().next();
            }
        } catch (Exception ex) {
            System.out.println("ex: " + ex);
        }
    }

    private void generateJson() {
        XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {
            @Override
            public HierarchicalStreamWriter createWriter(Writer writer) {
                return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
            }
        });
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.alias("item", MediaItem.class);
        json = xstream.toXML(item);

        //System.out.println(json);
    }

    public MediaItemBuilder build() {
        item = MediaItemCache.getItem(id);
        if (item == null) {
            executeQuery();
            createMediaItem();
        }
        if (item != null) {
            generateJson();
        }
        return this;
    }

    public String get() {
        return this.json;
    }

    private void createMediaItem() {
        if (originalEntry != null) {
            item = convertMediaItem(originalEntry);
            MediaItemCache.addItem(item);
        }
    }
}
