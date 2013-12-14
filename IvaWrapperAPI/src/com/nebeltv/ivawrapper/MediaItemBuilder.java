package com.nebeltv.ivawrapper;

import com.nebeltv.ivawrapper.xmlparser.nodes.Entry;
import com.nebeltv.ivawrapper.xmlparser.nodes.Feed;
import com.nebeltv.ivawrapper.xmlparser.nodes.Link;
import com.nebeltv.ivawrapper.xmlparser.nodes.helpers.LinkTitles;
import com.nebeltv.ivawrapper.xmlparser.nodes.Properties;
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
public class MediaItemBuilder {

	public static final String MEDIA_ITEM_QUERY = "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(#777)?"
					+ "$expand=Poster,Description,Director&Developerid=2A702798-6DBA-417D-A8BC-175CAEFFD2D6";//B43BF933-5CB5-434A-B0A8-717FC149FBED";

	private final String queryUrl;
	private Entry originalEntry;
	private MediaItem item;
	private String json;

	public MediaItemBuilder(Integer id) {
		this.queryUrl = MEDIA_ITEM_QUERY.replace("#777", "" + id);
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
		executeQuery();
		createMediaItem();
		generateJson();
		return this;
	}

	public String get() {
		return this.json;
	}

	private void createMediaItem() {
		if (originalEntry != null) {
			item = new MediaItem();

			if (originalEntry.getLinks() != null) {
				//item.setAuthor(originalEntry.getAuthor().getName());
				item.setAuthor(getDirectorName(originalEntry));
				item.setImage(getPoster(originalEntry));
				item.setDescr(getDescription(originalEntry));
			}

			if (originalEntry.getContent() != null && originalEntry.getContent().getProperties() != null) {
				Properties props = originalEntry.getContent().getProperties();
				item.setTitle(props.getDisplayTitle());
				item.setMediaId(props.getPublishedId());
			}

			item.setDate(originalEntry.getPublishedDate());
		}
	}

	private String getDirectorName(Entry entity) {
		String name = "some author " + ((int) (Math.random() * 1000));
		for (Link link : originalEntry.getLinks()) {
			if (LinkTitles.DIRECTOR.equals(link.getTitle())) {
				return link.getValue();
			}
		}
		return name;
	}

	private String getPoster(Entry entity) {
		String name = "some poster " + ((int) (Math.random() * 1000));
		for (Link link : originalEntry.getLinks()) {
			if (LinkTitles.POSTER.equals(link.getTitle())) {
				return link.getValue();
			}
		}
		return name;
	}

	private String getDescription(Entry entity) {
		String name = "some description " + ((int) (Math.random() * 1000));
		for (Link link : originalEntry.getLinks()) {
			if (LinkTitles.DESCRIPTION.equals(link.getTitle())) {
				return link.getValue();
			}
		}
		return name;
	}
}