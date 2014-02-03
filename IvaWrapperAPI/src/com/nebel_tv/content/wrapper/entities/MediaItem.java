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
 * <p>
 * <b>rating</b> - media rating range [0.0 - 5.0]
 * </p>
 * <p>
 * <b>imdb_rating</b> - IMDb media rating range [0.0 - 10.0]
 * </p>
 * <p>
 * <b>duration</b> -  media duration, format hh:mm:ss
 * </p>
 * <p>
 * <b>tagline</b> -  comma separated media tags 
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
    
    private String rating;
    private String imdb_rating;
    
    private String duration;
    private String tagline;

    /**
     * 
     */
    public MediaItem() {
  
        rating = String.format("%.02f", (float) Math.random() * 5);
        imdb_rating = String.format("%.02f", (float) Math.random() * 10);
        
        long length = (long) (Math.random() * 3* 3600);        
        duration = String.format("%d:%02d:%02d", length/3600, (length%3600)/60, (length%60));
        
        tagline = "18+";
    }
    
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
     * @return the Media item description
     */
    public String getDescr() {
        return descr;
    }

    /**
     * @param descr the value to set
     */
    public void setDescr(String value) {
        this.descr = descr;
    }
    
    /**
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param value The duration to set
     */
    public void setDuration(String value) {
        this.duration = value;
    }    
    
    /**
     * @return the tagline
     */
    public String getTagline() {
        return tagline;
    }

    /**
     * @param value The tagline to set
     */
    public void setTagline(String value) {
        this.tagline = value;
    }
    
    /**
     * @return the rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * @param value The rating to set
     */
    public void setRating(String value) {
        this.rating = value;
    } 
    
    /**
     * @return the IMDb rating
     */
    public String getImdbRating() {
        return imdb_rating;
    }

    /**
     * @param value The IMDb rating to set
     */
    public void setImdbRating(String value) {
        this.imdb_rating = value;
    }    
}
