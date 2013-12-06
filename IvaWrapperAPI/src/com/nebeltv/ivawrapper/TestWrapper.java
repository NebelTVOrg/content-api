package com.nebeltv.ivawrapper;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dmitry
 */
class TestWrapper implements IIvaWrapper {

    private final static TestWrapper instance = new TestWrapper();
    private final static Map<String, String> mediasData = new HashMap<String, String>();
    private final static Map<Integer, String> mediaItems = new HashMap<Integer, String>();

    static {
        // media items
        String media0 = "{\"media_id\":0,"
                + "\"image\":\"http://upload.wikimedia.org/wikipedia/commons/8/83/Red_2007.jpg\","
                + "\"title\":\"RED LANTERN\","
                + "\"author\":\"author 1\","
                + "\"date\":\"1 minute ago\","
                + "\"descr\":\"RED LANTERN description\"}";

        String media1 = "{\"media_id\":1,"
                + "\"image\":\"http://upload.wikimedia.org/wikipedia/commons/9/96/Dwarf_cavendish_leaf.JPG\","
                + "\"title\":\"GREEN LANTERN\","
                + "\"author\":\"author 2\","
                + "\"date\":\"10 minute ago\","
                + "\"descr\":\"GREEN LANTERN description\"}";

        String media2 = "{\"media_id\":2, "
                + "\"image\":\"http://upload.wikimedia.org/wikipedia/commons/6/65/Blue_morpho_butterfly.jpg\","
                + "\"title\":\"BLUE LANTERN\","
                + "\"author\":\"author 3\","
                + "\"date\":\"20 minute ago\","
                + "\"descr\":\"BLUE LANTERN description\"}";

        String media3 = "{\"media_id\":3,"
                + "\"image\":\"http://upload.wikimedia.org/wikipedia/commons/8/83/Red_2007.jpg\","
                + "\"title\":\"RED LANTERN 2\","
                + "\"author\":\"author 21\","
                + "\"date\":\"21 minute ago\","
                + "\"descr\":\"RED LANTERN 2 description\"}";

        String media4 = "{\"media_id\":4,"
                + "\"image\":\"http://upload.wikimedia.org/wikipedia/commons/9/96/Dwarf_cavendish_leaf.JPG\","
                + "\"title\":\"GREEN LANTERN 2\","
                + "\"author\":\"author 22\","
                + "\"date\":\"12 minute ago\","
                + "\"descr\":\"GREEN LANTERN 2 description\"}";

        String media5 = "{\"media_id\":5,"
                + "\"image\":\"http://upload.wikimedia.org/wikipedia/commons/6/65/Blue_morpho_butterfly.jpg\","
                + "\"title\":\"BLUE LANTERN 2\","
                + "\"author\":\"author 23\","
                + "\"date\":\"22 minute ago\","
                + "\"descr\":\"BLUE LANTERN 2 description\"}";

        // media data
        mediasData.put("1", '['
                + media0 + ","
                + media1 + ","
                + media2
                + "]");

        mediasData.put("2", '['
                + media3 + ","
                + media4 + ","
                + media5
                + "]");

        mediaItems.put(0, media0);
        mediaItems.put(1, media1);
        mediaItems.put(2, media2);
        mediaItems.put(3, media3);
        mediaItems.put(4, media4);
        mediaItems.put(5, media5);
    }

    private TestWrapper() {
    }

    @Override
    public String getMedias(Integer n, Integer skip, String category, String viewType, String viewTypePeriod) {
        return mediasData.get(category);
    }

    @Override
    public String getMediaItem(Integer id) {
        return mediaItems.get(id);
    }

    public static TestWrapper getInstance() {
        return instance;
    }

}
