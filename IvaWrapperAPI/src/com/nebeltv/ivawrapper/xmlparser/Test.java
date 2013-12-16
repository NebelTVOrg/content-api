package com.nebeltv.ivawrapper.xmlparser;

import com.nebeltv.ivawrapper.xmlparser.nodes.Feed;
import com.nebeltv.ivawrapper.xmlparser.nodes.Entry;
import com.nebeltv.ivawrapper.ConnectionHelper;
import com.nebeltv.ivawrapper.LiveWrapper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.io.xml.XppReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author dst
 */
public class Test {

	public static String getContents(File aFile) {
		String fileData = "";
		try {
			final FileReader reader = new FileReader(aFile);
			fileData = getReaderString(reader);
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
		}
		return fileData;
	}

	public static void main(String[] args) throws Exception {
		final String medias = new LiveWrapper().getMedias(3, 105, "1", null, null);
		System.out.println("getMedias(3, 105, \"1\", null, null): \n" + medias);
		final String mediaItem = new LiveWrapper().getMediaItem(13);
		System.out.println("getMediaItem(13): \n" + mediaItem);
	}

	public static void test1() throws Exception {

		XStream xStream = new XStream(new XppDriver());
		xStream.alias("entry", Entry.class);
		xStream.alias("feed", Feed.class);
		xStream.autodetectAnnotations(true);
		xStream.ignoreUnknownElements();

		String url = "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(749049)?"
						+ "$expand=Poster,Description,Director&Developerid=2A702798-6DBA-417D-A8BC-175CAEFFD2D6";//B43BF933-5CB5-434A-B0A8-717FC149FBED";

		XppReader reader = ConnectionHelper.getXppStreamReader(url);

		Feed feed;
		Object result = xStream.unmarshal(reader);
		if (result instanceof Feed) {
			feed = (Feed) result;
		} else if (result instanceof Entry) {
			feed = new Feed();
			feed.addEntry((Entry) result);
		} else {
			feed = new Feed();
		}
		System.out.println(feed.toString());

		//System.out.println("xml response: " + getReaderString(ConnectionHelper.getStreamReader(url)));
	}

	/**
	 *
	 * @param reader
	 * @return
	 */
	public static String getReaderString(final Reader reader) {
		StringBuilder contents = new StringBuilder();
		BufferedReader input = new BufferedReader(reader);
		try {
			String line;
			while ((line = input.readLine()) != null) {
				contents.append(line);
			}
		} catch (IOException exception) {
		}
		return contents.toString();
	}
}
