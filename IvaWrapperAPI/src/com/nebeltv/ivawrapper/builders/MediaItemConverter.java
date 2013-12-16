/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nebeltv.ivawrapper.builders;

import com.nebeltv.ivawrapper.MediaItem;
import com.nebeltv.ivawrapper.xmlparser.nodes.Entry;
import com.nebeltv.ivawrapper.xmlparser.nodes.Link;
import com.nebeltv.ivawrapper.xmlparser.nodes.Properties;
import com.nebeltv.ivawrapper.xmlparser.nodes.helpers.LinkTitles;

/**
 *
 * @author dtv
 */
public class MediaItemConverter {

	public MediaItem convertMediaItem(Entry entry) {
		MediaItem item = null;
		if (entry != null) {
			item = new MediaItem();

			if (entry.getLinks() != null) {
				//item.setAuthor(entry.getAuthor().getName());
				item.setAuthor(getDirectorName(entry));
				item.setImage(getPoster(entry));
				item.setDescr(getDescription(entry));
			}

			if (entry.getContent() != null && entry.getContent().getProperties() != null) {
				Properties props = entry.getContent().getProperties();
				item.setTitle(props.getDisplayTitle());
				item.setMediaId(props.getPublishedId());
			}

			item.setDate(entry.getPublishedDate());
		}
		return item;
	}

	public String getDirectorName(Entry entry) {
		String name = "some author " + ((int) (Math.random() * 1000));
		for (Link link : entry.getLinks()) {
			if (LinkTitles.DIRECTOR.equals(link.getTitle())) {
				return link.getValue();
			}
		}
		return name;
	}

	public String getPoster(Entry entry) {
		String name = "some poster " + ((int) (Math.random() * 1000));
		for (Link link : entry.getLinks()) {
			if (LinkTitles.POSTER.equals(link.getTitle())) {
				return link.getValue();
			}
		}
		return name;
	}

	public String getDescription(Entry entry) {
		String name = "some description " + ((int) (Math.random() * 1000));
		for (Link link : entry.getLinks()) {
			if (LinkTitles.DESCRIPTION.equals(link.getTitle())) {
				return link.getValue();
			}
		}
		return name;
	}
}
