content-api
===========
Content API is simple JAVA interface for wrapping the IVA oData API (http://www.internetvideoarchive.com/data-apis/odata-api-1-0)  and return it query results to the javascript client in the JSON format. In future this wrapper will also implement simple caching functionality, so not every request to the IVA API will be executed against web server, but sometime may return cached item (images, query results, etc.).

It is also envisioned that at later stage IVA oData API will be expanded by other third-party API for media comments, ratings or recommendations (e.g. http://www.programmableweb.com/api/unofficial-imdb or http://www.programmableweb.com/api/filmaster).

This module will be finally used as the part of native Android application (https://github.com/NebelTVOrg/nebel.tv-teaser), so only Java classes required. For the development and testing easiness the same Java classes to be wrapped into the Web Service (i.e. RESTful Web Services). 

How to use test wrapper:

all possible usages for current test wrapper in example - 

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

