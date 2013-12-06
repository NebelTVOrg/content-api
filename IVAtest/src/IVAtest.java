/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.nebeltv.ivawrapper.IIvaWrapper;
import com.nebeltv.ivawrapper.Wrapper;
import com.nebeltv.ivawrapper.WrapperTypes;

/**
 *
 * @author dmitry
 */
public class IVAtest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IIvaWrapper w = Wrapper.getWrapper(WrapperTypes.TEST);
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
    }

}