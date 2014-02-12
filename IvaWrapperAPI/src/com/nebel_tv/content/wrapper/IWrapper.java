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
package com.nebel_tv.content.wrapper;

/**
 *
 */
public interface IWrapper {

    /**
     * Method: getMedias
     *
     * @param n - number of items to return [integer]
     * @param skip - number of how much items to skip [integer]
     * @param category - media category (films, series, images, etc) [1,2,3...]
     * or [string]
     * @param viewType - type of returned media (latest, best, most
     * commented....) [1,2,3...] or [string]
     * @param viewTypePeriod - period of type (for example, best for
     * today/week/month, etc..) ['today', 'week', 'month']
     *
     * @return array with items with such properties: media_id - unique id of
     * media, which can be used to retrieve full media information image - link
     * to media poster title - media title author - media author date - media
     * date [probably, not like mm/dd/yy, but 'minute ago', 'hour ago', today',
     * 'yesterday' and so forth] descr - media description
     */
    public String getMedias(Integer n, Integer skip, String category, String viewType, String viewTypePeriod);

    /**
     * Method: getMediaItem
     *
     * @param id - media id [integer]
     *
     * @return media_id - unique id of media, which can be used to retrieve full
     * media information image - link to media poster title - media title author
     * - media author date - media date [probably, not like mm/dd/yy, but
     * 'minute ago', 'hour ago', today', 'yesterday' and so forth] descr - media
     * description
     */
    public String getMediaItem(Integer id);
    
    /**
     * 
     * @param id media id [integer] unique id of media
     * @return 
     */
    public String getVideoAssets(Integer id);
}
