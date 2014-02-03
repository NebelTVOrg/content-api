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

import com.nebel_tv.content.wrapper.entities.MediaItem;
import com.nebel_tv.content.xmlparser.nodes.Entry;
import com.nebel_tv.content.xmlparser.nodes.Link;
import com.nebel_tv.content.xmlparser.nodes.Properties;
import com.nebel_tv.content.xmlparser.nodes.helpers.LinkTitles;

/**
 *
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
