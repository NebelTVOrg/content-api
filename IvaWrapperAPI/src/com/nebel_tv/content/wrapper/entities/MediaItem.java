/**
 * Copyright (C) 2014 Nebel TV (http://nebel.tv)
    
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
    
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
    
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.nebel_tv.content.wrapper.entities;

/**
 * <p>
 * <b>media_id</b> - unique id of media, which can be used to retrieve full
 * media information
 * </p>
 * <p>
 * <b>image</b> - link to media poster
 * </p>
 * <p>
 * <b>title</b> - media title
 * </p>
 * <p>
 * <b>author</b> - media author
 * </p>
 * <p>
 * <b>date</b> - media date [probably, not like mm/dd/yy, but 'minute ago',
 * 'hour ago', today', 'yesterday' and so forth]
 * </p>
 * <b>descr</b> - media description
 * </p>
 *
 */
public class MediaItem {

    private String media_id;
    private String image;
    private String title;
    private String author;
    private String date;
    private String descr;

    /**
     * @return the mediaId
     */
    public String getMediaId() {
        return media_id;
    }

    /**
     * @param mediaId the mediaId to set
     */
    public void setMediaId(String mediaId) {
        this.media_id = mediaId;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the descr
     */
    public String getDescr() {
        return descr;
    }

    /**
     * @param descr the descr to set
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }
}
