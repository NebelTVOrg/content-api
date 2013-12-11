/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nebeltv.ivawrapper.xmlparser;

import com.nebeltv.ivawrapper.ClientConnectionRelease;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.io.xml.XppReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author dmitry
 */
public class Test {

    public static String getContents(File aFile) {
        //...checks on aFile are elided
        StringBuilder contents = new StringBuilder();

        try {
            //use buffering, reading one line at a time
            //FileReader always assumes default encoding is OK!
            BufferedReader input = new BufferedReader(new FileReader(aFile));
            try {
                String line = null; //not declared within while loop
        /*
                 * readLine is a bit quirky :
                 * it returns the content of a line MINUS the newline.
                 * it returns null only for the END of the stream.
                 * it returns an empty String if two newlines appear in a row.
                 */
                while ((line = input.readLine()) != null) {
                    contents.append(line);
                }
            } finally {
                input.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return contents.toString();
    }

    public static void main(String[] args) throws Exception {

        XStream xStream = new XStream(new XppDriver());
        xStream.registerConverter(new FeedConverter());
        xStream.registerConverter(new EntryConverter());
        xStream.alias("entry", Entry.class);
        xStream.alias("feed", Feed.class);
        xStream.alias("id", Feed.class);
        //FileInputStream file = new FileInputStream("/home/dmitry/workspace/content-api/IvaWrapperAPI/src/resources/newXMLDocument.xml");
        //InputStreamReader inputStreamReader = new InputStreamReader(file);

        InputStreamReader inputStreamReader = new InputStreamReader(ClientConnectionRelease.getStream());
        XppReader reader = new XppReader(inputStreamReader);
//        String xml = getContents(file);
//        int begin = xml.indexOf("<entry>");
//        int end = xml.lastIndexOf("</entry>");
//        xml = xml.substring(begin, end + 8);
//        Entry person = (Entry) xStream.fromXML(xml);

        Object entry = xStream.unmarshal(reader);
        System.out.println();
    }
}
