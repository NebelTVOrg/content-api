/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nebeltv.ivawrapper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author STAS
 */
public class ClientConnectionRelease {

    /**
     * @param args the command line arguments
     */
    public static String fixURL(String url) {
        StringBuilder fixedURL = new StringBuilder();
        String[] s1 = url.split("\\?");
        fixedURL.append(s1[0] + "?");
        if (s1.length > 1) {
            String[] s2 = s1[1].split("&");
            for (int i = 0; i < s2.length; i++) {
                String[] s = s2[i].split("=");
                fixedURL.append("&" + s[0] + "=" + URLEncoder.encode(s[1]));
            }
        }
        return fixedURL.toString();
    }

    public final static void main(String[] args) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            String url = "http://api.internetvideoarchive.com/1.0/DataService/ReleaseEvents()?"
                    + "$filter=ReleaseDate ge datetime'2011-06-01' and ReleaseDate lt datetime'2011-07-01' and ReleaseTypeId eq 0"
                    + "&$expand=EntertainmentProgram&Developerid=B43BF933-5CB5-434A-B0A8-717FC149FBED";
            System.out.println(fixURL(url));
            HttpGet httpget = new HttpGet(fixURL(url));
//			String encodedURL = URLEncoder.encode("ReleaseDate ge datetime'2011-06-01' and ReleaseDate lt datetime'2011-07-01' and ReleaseTypeId eq 2");
//			HttpGet httpget = new HttpGet("http://api.internetvideoarchive.com/1.0/DataService/ReleaseEvents()?$filter=" + encodedURL
//							+ "&$expand=EntertainmentProgram&Developerid=B43BF933-5CB5-434A-B0A8-717FC149FBED");
            httpget.addHeader("Developerid", "B43BF933-5CB5-434A-B0A8-717FC149FBED");

            // Execute HTTP request
            System.out.println("executing request " + httpget.getURI());
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                System.out.println("----------------------------------------");

                // Get hold of the response entity
                HttpEntity entity = response.getEntity();

                // If the response does not enclose an entity, there is no need
                // to bother about connection release
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    BufferedReader br = new BufferedReader(new InputStreamReader(instream));

                    int symbol = br.read();
                    while (symbol != -1) {
                        System.out.print(Character.toChars(symbol));
                        symbol = br.read();
                    }
//					XStream xstream = new XStream();
//					Object obj = xstream.fromXML(instream);
                }
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

    public static InputStream getStream(/*String url*/) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            String url = "http://api.internetvideoarchive.com/1.0/DataService/ReleaseEvents()?"
                    + "$filter=ReleaseDate ge datetime'2011-06-01' and ReleaseDate lt datetime'2011-07-01' and ReleaseTypeId eq 0"
                    + "&$expand=EntertainmentProgram&Developerid=B43BF933-5CB5-434A-B0A8-717FC149FBED";
            System.out.println(fixURL(url));
            HttpGet httpget = new HttpGet(fixURL(url));
//			String encodedURL = URLEncoder.encode("ReleaseDate ge datetime'2011-06-01' and ReleaseDate lt datetime'2011-07-01' and ReleaseTypeId eq 2");
//			HttpGet httpget = new HttpGet("http://api.internetvideoarchive.com/1.0/DataService/ReleaseEvents()?$filter=" + encodedURL
//							+ "&$expand=EntertainmentProgram&Developerid=B43BF933-5CB5-434A-B0A8-717FC149FBED");
            httpget.addHeader("Developerid", "B43BF933-5CB5-434A-B0A8-717FC149FBED");

            // Execute HTTP request
            System.out.println("executing request " + httpget.getURI());
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                System.out.println("----------------------------------------");

                // Get hold of the response entity
                HttpEntity entity = response.getEntity();

                // If the response does not enclose an entity, there is no need
                // to bother about connection release
                if (entity != null) {
                    return entity.getContent();
                }
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        return null;
    }
}
