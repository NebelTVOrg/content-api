/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nebeltv.ivawrapper;

/**
 *
 * @author dmitry
 */
public class WrapperUtils {

    /**
     * safe String to Integer converter
     *
     * @param number string to parse
     * @return parsed Integer or null
     */
    public static Integer getInt(final String number) {
        try {
            return Integer.parseInt(number);
        } catch (Exception e) {
            System.out.println("e: " + e);
        }
        return null;
    }
}
