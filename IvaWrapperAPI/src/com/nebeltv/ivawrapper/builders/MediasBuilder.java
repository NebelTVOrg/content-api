/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nebeltv.ivawrapper.builders;

import com.nebeltv.ivawrapper.ConnectionHelper;
import com.nebeltv.ivawrapper.entities.MediaItem;
import com.nebeltv.ivawrapper.xmlparser.nodes.Entry;
import com.nebeltv.ivawrapper.xmlparser.nodes.Feed;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.io.xml.XppReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author stas
 */
public class MediasBuilder extends MediaItemConverter {

	public static final String MEDIAS_QUERY_PART_1 = "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms()?$skip={skip}&$top={top}&";
	public static final String MEDIAS_QUERY_PART_2 = "$filter=MediaId eq {MediaId}&";
	public static final String MEDIAS_QUERY_PART_3 = "$expand=Poster,Description,Director&Developerid=2A702798-6DBA-417D-A8BC-175CAEFFD2D6";//B43BF933-5CB5-434A-B0A8-717FC149FBED";
	private String queryUrl;
	private final List<Entry> entries = new ArrayList<Entry>();
	private final List<MediaItem> items = new ArrayList<MediaItem>();
	private String json;

	public MediasBuilder(Integer n, Integer skip, String category, String viewType, String viewTypePeriod) {
		this.queryUrl = MEDIAS_QUERY_PART_1;
		if (n == null || n < 1) {
			n = 5;
		}
		if (skip == null || skip < 0) {
			skip = 0;
		}
		this.queryUrl = this.queryUrl.replace("{top}", n.toString());
		this.queryUrl = this.queryUrl.replace("{skip}", skip.toString());
		if (StringUtils.isNotBlank(category)) {
			this.queryUrl += MEDIAS_QUERY_PART_2.replace("{MediaId}", "" + category);
		}
		this.queryUrl += MEDIAS_QUERY_PART_3;
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
				entries.addAll(feed.getEntries());
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
		json = xstream.toXML(items);

		//System.out.println(json);
	}

	public MediasBuilder build() {
		executeQuery();
		createMediaItems();
		generateJson();
		return this;
	}

	public String get() {
		return this.json;
	}

	private void createMediaItems() {
		Iterator<Entry> it = entries.iterator();
		while (it.hasNext()) {
			Entry entry = it.next();
			MediaItem item = convertMediaItem(entry);
			items.add(item);
		}
	}
}
