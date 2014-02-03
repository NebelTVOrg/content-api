nebel.tv-content-api
===========
Content API is simple JAVA interface for wrapping the IVA oData API (http://www.internetvideoarchive.com/data-apis/odata-api-1-0)  and return it query results to the javascript client in the JSON format. In future this wrapper will also implement simple caching functionality, so not every request to the IVA API will be executed against web server, but sometime may return cached item (images, query results, etc.).

It is also envisioned that at later stage IVA oData API will be expanded by other third-party API for media comments, ratings or recommendations (e.g. http://www.programmableweb.com/api/unofficial-imdb or http://www.programmableweb.com/api/filmaster).

This module will be finally used as the part of native Android application (https://github.com/NebelTVOrg/nebel.tv-teaser), so only Java classes required. For the development and testing easiness the same Java classes to be wrapped into the Web Service (i.e. RESTful Web Services). 


Wrapper now in LIVE mode, and works with IVA services using free developer key.

How to use live wrapper (examples):

Java code:

	IIvaWrapper w = Wrapper.getWrapper(WrapperTypes.LIVE);
	String item = w.getMediaItem(1);
	System.out.println(item);
	item = w.getMediaItem(2);
	System.out.println(item);
	item = w.getMediaItem(3);
	System.out.println(item);
	item = w.getMediaItem(4);
	System.out.println(item);

	item = w.getMedias(10, 50, "1", null, null);
	System.out.println(item);
	item = w.getMedias(0, 2, "3", null, null);
	System.out.println(item);


Content Wrapper Web Service links:

 - http://54.201.170.111:8080/IvaWrapperWeb/getMedias?skip=100&n=3&category=0    
	Possible values: 
	 - n - up to 500 (entries)
	 - skip - any integer
	 - category - not provided, or any value from this table - http://www.internetvideoarchive.com/media-type-map-to-mediaids/
 
 - http://[server]:8080/IvaWrapperWeb/getMediaItem?n=2
	Possible values:
	- server - content API wrapper server
	- port - server port, 8080 by default
	- n - any valid publishedId


JavaScript code:

	function reqListener () {
		console.log(this.responseText);
	};

	var oReq = new XMLHttpRequest();
	oReq.onload = reqListener;
	oReq.open("get", "http://54.201.170.111:8080/IvaWrapperWeb/getMediaItem?n=2", true);
	oReq.send();
	
	var oReq = new XMLHttpRequest();
	oReq.onload = reqListener;
	oReq.open("get", "http://54.201.170.111:8080/IvaWrapperWeb/getMedias?skip=100&n=3&category=0", true);
	oReq.send();
	
Expected results:
	
	http://54.201.170.111:8080/IvaWrapperWeb/getMediaItem?n=2
	
	{
	  "media_id": "2",
	  "image": "http://content.internetvideoarchive.com/content/posters/005/223_562.jpg",
	  "title": "On The Town",
	  "author": "Stanley Donen",
	  "date": "2013-11-18T15:06:00Z",
	  "descr": "Three sailors team up to find the beautiful poster girl whose picture they saw in a New York City subway. Oscar-winning score by Bernstein, Comden and Green!"
	  "rating": "3.54",
	  "imdb_rating": "7.70", 
	  "duration": "1:27:08", 
	  "tagline": "Comedy, Musical, Romance" 	  
	}

	
	http://54.201.170.111:8080/IvaWrapperWeb/getMedias?skip=100&n=3&category=0
	
	[
	  {
		"media_id": "114",
		"image": "http://content.internetvideoarchive.com/content/posters/004/208_930.jpg",
		"title": "At The Circus",
		"author": "Edward Buzzell",
		"date": "2013-10-25T15:51:00Z",
		"descr": "A circus owner enlists the Marx Brothers' aid in saving his run-down circus from bankruptcy."
		"rating": "4.07",
		"imdb_rating": "6.90", 
		"duration": "1:27:08", 
		"tagline": "Comedy, Musical" 
	  },
	  {
		"media_id": "115",
		"image": "http://content.internetvideoarchive.com/content/posters/002/85_517.jpg",
		"title": "The Blue Max",
		"author": "John Guillermin",
		"date": "2013-11-27T12:05:00Z",
		"descr": "During World War I, a young German aviator competes with other members of his squadron for the coveted Blue Max flying award. Terrific aerial photography!"
		"rating": "3.35",
		"imdb_rating": "7.10", 
		"duration": "1:50:30", 
		"tagline": "Adventure, Drama, War" 
	  },
	  {
		"media_id": "116",
		"image": "http://content.internetvideoarchive.com/content/posters/006/253_205.jpg",
		"title": "The Barkleys Of Broadway",
		"author": "Charles Walters",
		"date": "2013-10-28T11:15:00Z",
		"descr": "A theatrical couple temporarily splits up over a disagreement. Astaire and Rogers' last film together!"
		"rating": "4.70",
		"imdb_rating": "7.0", 
		"duration": "1:49:00", 
		"tagline": "Comedy, Musical" 
	  }
	]

