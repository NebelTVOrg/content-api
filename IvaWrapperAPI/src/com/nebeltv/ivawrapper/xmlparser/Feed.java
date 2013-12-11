/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nebeltv.ivawrapper.xmlparser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dmitry
 */
public class Feed {

    public String id;
    public String title;
    public String updated;
    public String link;
    private List<Entry> entries = new ArrayList<>();

    void addEntry(Entry entry) {
        entries.add(entry);
    }
}
