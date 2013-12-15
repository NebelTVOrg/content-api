package com.nebeltv.ivawrapper;

/**
 *
 * @author dst
 */
public class LiveWrapper implements IIvaWrapper {

	public LiveWrapper() {
	}

	public String getMedias(Integer n, Integer skip, String category, String viewType, String viewTypePeriod) {
		return new MediasBuilder(n, skip, category, viewType, viewTypePeriod).build().get();
	}

	@Override
	public String getMediaItem(Integer id) {
		return new MediaItemBuilder(id).build().get();
	}
}
