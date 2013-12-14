package com.nebeltv.ivawrapper;

/**
 *
 * @author dst
 */
public class LiveWrapper implements IIvaWrapper {

	public LiveWrapper() {
	}

	@Override
	public String getMedias(Integer n, Integer skip, String category, String viewType, String viewTypePeriod) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String getMediaItem(Integer id) {
		return new MediaItemBuilder(id).build().get();
	}

}
