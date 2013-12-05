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
public class Wrapper {

    public static IIvaWrapper getWrapper(int type) {
        if (WrapperTypes.TEST == type) {
            return TestWrapper.getInstance();
        }
        if (WrapperTypes.LIVE == type) {
            return new LiveWrapper();
        }
        return null;
    }
}
