package com.nebeltv.ivawrapper.builders;

import com.nebeltv.ivawrapper.ConnectionHelper;
import com.nebeltv.ivawrapper.entities.MediaItem;
import com.nebeltv.ivawrapper.cache.MediaItemCache;
import com.nebeltv.ivawrapper.xmlparser.nodes.Entry;
import com.nebeltv.ivawrapper.xmlparser.nodes.Feed;
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

	//B43BF933-5CB5-434A-B0A8-717FC149FBED";
	public static final String MEDIA_ITEM_QUERY = "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms({publishedId})?"
					+ "$expand=Poster,Description,Director&Developerid=2A702798-6DBA-417D-A8BC-175CAEFFD2D6";

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
