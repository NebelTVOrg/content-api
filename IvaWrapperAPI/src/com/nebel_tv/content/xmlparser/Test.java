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
package com.nebel_tv.content.xmlparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.nebel_tv.content.utils.ConnectionUtils;
import com.nebel_tv.content.wrapper.ConnectionHelper;
import com.nebel_tv.content.wrapper.LiveWrapper;
import com.nebel_tv.content.xmlparser.nodes.Entry;
import com.nebel_tv.content.xmlparser.nodes.Feed;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * @warning: move into the test package
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
        final String mediaItemAbsent = new LiveWrapper().getMediaItem(-13);
        System.out.println("getMediaItem(2): \n" + mediaItemAbsent);
    }

    public static void test1() throws Exception {

        XStream xStream = new XStream(new XppDriver());
        xStream.alias("entry", Entry.class);
        xStream.alias("feed", Feed.class);
        xStream.autodetectAnnotations(true);
        //xStream.ignoreUnknownElements();

        String url = "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(749049)?"
                + "$expand=Poster,Description,Director&Developerid=2A702798-6DBA-417D-A8BC-175CAEFFD2D6";//B43BF933-5CB5-434A-B0A8-717FC149FBED";

        Feed feed;
        Object result = xStream.fromXML(ConnectionUtils.getResponseAsString(ConnectionHelper.fixURL(url)));
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
