/*
 ==== WRAPPER INTERFACE ======

 */
package com.nebeltv.ivawrapper;

/**
 *
 * @author dst
 */
public interface IIvaWrapper {

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
}
