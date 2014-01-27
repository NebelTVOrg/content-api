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
package com.nebel_tv.client;

import com.nebel_tv.content.wrapper.IWrapper;
import com.nebel_tv.content.wrapper.Wrapper;
import com.nebel_tv.content.wrapper.WrapperTypes;

/**
 *
 */
public class ContentWrapperClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IWrapper w = Wrapper.getWrapper(WrapperTypes.TEST);
        String item = w.getMediaItem(0);
        System.out.println(item);
        item = w.getMediaItem(1);
        System.out.println(item);
        item = w.getMediaItem(2);
        System.out.println(item);
        item = w.getMediaItem(3);
        System.out.println(item);
        item = w.getMediaItem(4);
        System.out.println(item);
        item = w.getMediaItem(5);
        System.out.println(item);

        item = w.getMedias(0, 0, "1", null, null);
        System.out.println(item);
        item = w.getMedias(0, 0, "2", null, null);
        System.out.println(item);

        System.out.println("\n\n===============================================\n\n");

        w = Wrapper.getWrapper(WrapperTypes.LIVE);
        item = w.getMediaItem(1);
        System.out.println(item);
        item = w.getMediaItem(2);
        System.out.println(item);
        item = w.getMediaItem(3);
        System.out.println(item);
        item = w.getMediaItem(4);
        System.out.println(item);

        item = w.getMedias(0, 0, "1", null, null);
        System.out.println(item);
        item = w.getMedias(0, 10, "0", null, null);
        System.out.println(item);
        item = w.getMedias(2, 9, "0", null, null);
        System.out.println(item);
    }
}
