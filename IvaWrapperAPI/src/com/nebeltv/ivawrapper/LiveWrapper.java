package com.nebeltv.ivawrapper;

import com.nebeltv.ivawrapper.builders.MediasBuilder;
import com.nebeltv.ivawrapper.builders.MediaItemBuilder;

/**
 *
 * @author dst
 */
public class LiveWrapper implements IIvaWrapper {

	public LiveWrapper() {
	}

	@Override
	public String getMedias(Integer n, Integer skip, String category, String viewType, String viewTypePeriod) {
		return new MediasBuilder(n, skip, category, viewType, viewTypePeriod).build().get();
	}

	@Override
	public String getMediaItem(Integer id) {
		if (id != null) {
			return new MediaItemBuilder(id.toString()).build().get();
		}
		return "-1";
	}
}
