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
package com.nebel_tv.content.cache;

import com.nebel_tv.content.wrapper.entities.MediaItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class MediasCache {

    private static final HashMap<String, HashMap<List<Integer>, List<String>>> rangeByCategory = new HashMap<String, HashMap<List<Integer>, List<String>>>();

    private static List<Integer> getRange(Integer n, Integer skip) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(skip);
        list.add(skip + n);
        return list;
    }

    //returns cached range if there is cached range that
    //includes new range and null if there is no such cached range
    private static List<Integer> includesRange(List<Integer> range, String category) {

        if (rangeByCategory.get(category) == null) {
            return null;
        } else {
            for (Map.Entry<List<Integer>, List<String>> entry : rangeByCategory.get(category).entrySet()) {
                int from = entry.getKey().get(0);
                int to = entry.getKey().get(1);
                if (from <= range.get(0) && to >= range.get(1)) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }

    public static List<String> getItemsID(Integer n, Integer skip, String category) {
        List<Integer> currRange = getRange(n, skip);
        List<Integer> includingRange = includesRange(currRange, category);
        if (includingRange != null) {
            int from = currRange.get(0) - includingRange.get(0);
            int to = includingRange.size() - currRange.get(0);
            return rangeByCategory.get(category).get(includingRange).subList(from, to);
        } else {
            return null;
        }
    }

    public static void addMedias(Integer n, Integer skip, String category, List<MediaItem> medias) {
        List<Integer> rangeToAdd = getRange(n, skip);
        List<Integer> intersectLeft = null;
        List<Integer> intersectRight = null;
        List<List<Integer>> included = new ArrayList<List<Integer>>();

        HashMap<List<Integer>, List<String>> idByRange = rangeByCategory.get(category);
        if (idByRange == null) {
            idByRange = new HashMap<List<Integer>, List<String>>();
            rangeByCategory.put(category, idByRange);
        }

        int newFrom = rangeToAdd.get(0);
        int newTo = rangeToAdd.get(1);

        //find intervals of intersection cached range with new range
        for (Map.Entry<List<Integer>, List<String>> entry : idByRange.entrySet()) {
            List<Integer> range = entry.getKey();
            int from = range.get(0);
            int to = range.get(1);
            if (from < newFrom && to >= newFrom) {
                intersectLeft = range;
            } else if (to > newTo && from <= newFrom) {
                intersectRight = range;
            } else if (from >= newFrom && to <= newTo) {
                included.add(range);
            }
        }

        if (intersectLeft == null && intersectRight == null && included.size() == 0) {
            List<String> cashedMedias = new ArrayList<String>();

            for (MediaItem item : medias) {
                MediaItemCache.addItem(item);
                cashedMedias.add(item.getMediaId());
            }

            idByRange.put(rangeToAdd, cashedMedias);
        } else {

            //merges cached and new rages if intersection is on left sight
            //adds new items to MediaItemCache
            if (intersectLeft != null) {
                int oldFrom = intersectLeft.get(0);
                int oldTo = intersectLeft.get(1);
                int toSkip = oldTo - newFrom + 1;
                List<String> cashedMedias = idByRange.get(intersectLeft);
                List<Integer> newRange = new ArrayList<Integer>(2);
                newRange.add(oldFrom);
                newRange.add(newTo);

                for (int i = toSkip; i < medias.size(); i++) {
                    MediaItem item = medias.get(i);
                    MediaItemCache.addItem(item);
                    cashedMedias.add(item.getMediaId());
                }
                idByRange.remove(intersectLeft);
                idByRange.put(newRange, cashedMedias);
            }

            //merges cached and new rages if intersection is on right sight
            //adds new items to MediaItemCache
            if (intersectRight != null) {
                int oldFrom = intersectRight.get(0);
                int oldTo = intersectRight.get(1);
                int toSkip = newTo - oldFrom + 1;
                List<String> cashedMedias = idByRange.get(intersectRight);
                List<Integer> newRange = new ArrayList<Integer>(2);
                newRange.add(newFrom);
                newRange.add(oldTo);

                for (int i = 0; i < medias.size() - toSkip; i++) {
                    MediaItem item = medias.get(i);
                    MediaItemCache.addItem(item);
                    cashedMedias.add(i, item.getMediaId());
                }
                idByRange.remove(intersectRight);
                idByRange.put(newRange, cashedMedias);
            }

            //replaces cached range by new range if new range includes cached range
            //adds new items to MediaItemCache
            if (included.size() > 0) {

                List<String> cashedMedias = new ArrayList<String>();

                for (int i = 0; i < included.size(); i++) {
                    idByRange.remove(included.get(i));
                }

                for (MediaItem item : medias) {
                    MediaItemCache.addItem(item);
                    cashedMedias.add(item.getMediaId());
                }

                idByRange.put(rangeToAdd, cashedMedias);
            }
        }
    }
}
