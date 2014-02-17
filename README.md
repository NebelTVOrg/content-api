nebel.tv-content-api
===========
Content API is simple JAVA interface for wrapping the IVA oData API (http://www.internetvideoarchive.com/data-apis/odata-api-1-0)  and return it query results to the javascript client in the JSON format. In future this wrapper will also implement simple caching functionality, so not every request to the IVA API will be executed against web server, but sometime may return cached item (images, query results, etc.).

It is also envisioned that at later stage IVA oData API will be expanded by other third-party API for media comments, ratings or recommendations (e.g. http://www.programmableweb.com/api/unofficial-imdb or http://www.programmableweb.com/api/filmaster).

This module will be finally used as the part of native Android application (https://github.com/NebelTVOrg/nebel.tv-teaser), so only Java classes required. For the development and testing easiness the same Java classes to be wrapped into the Web Service (i.e. RESTful Web Services). 


Wrapper now in LIVE mode, and works with IVA services using free developer key.

How to use live wrapper ver.1.0.5 (examples):

 **Java code:**
```java
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
	
	item = w.getVideoAssets(7);
	System.out.println(item);
```

Content Wrapper Web Service links:

 - http://[server:port]/IvaWrapperWeb/getMedias?skip=100&n=3&category=0    
<br>Possible values:
	 - n - up to 500 (entries)
	 - skip - any integer
	 - category - not provided, or any value from this table - http://www.internetvideoarchive.com/media-type-map-to-mediaids/
 
 - http://[server:port]/IvaWrapperWeb/getMediaItem?n=2
<br>Possible values:
	- server - content API wrapper server
	- port - server port, 8080 by default
	- n - any valid publishedId

 - http://[server:port]/IvaWrapperWeb/getVideoAssets?id=0
<br>Where:
 	- id - media item id (PublishedID)

Reference: www.internetvideoarchive.com/how-to-generate-urls-to-video-content-using-iva-odata-api

 **JavaScript code:**
```java
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
	
	var oReq = new XMLHttpRequest();
	oReq.onload = reqListener;
	oReq.open("get", "http://54.201.170.111:8080/IvaWrapperWeb/getVideoAssets?id=0", true);
	oReq.send();
```

 **Expected results:**

http://54.201.170.111:8080/IvaWrapperWeb/getMediaItem?n=2
```json
{
	  
}
```
http://54.201.170.111:8080/IvaWrapperWeb/getMedias?skip=100&n=3&category=0
```json
[
]
```	
http://54.201.170.111:8080/IvaWrapperWeb/getVideoAssets?id=7
```json
[
    {
        "DateDigitized": "/Date(1356587061000)/",
        "StreamingFlavorId": 24,
        "ProprietaryCustomerId": -1,
        "RemoteURL": "false",
        "rate": 750,
        "PublishedId": 7,
        "FileType": "mp4",
        "ID": "7-24",
        "URL": "http://54.201.170.111/assets/001-720p-2500kb.mp4",
        "__metadata": {
            "id": "http://api.internetvideoarchive.com/1.0/DataService/Encodes('7-24')",
            "type": "MediaModel.Encode",
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/Encodes('7-24')"
        },
        "VideoAsset": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/Encodes('7-24')/VideoAsset"
            }
        }
    },
    {
        "DateDigitized": "/Date(1345468075347)/",
        "StreamingFlavorId": 40,
        "ProprietaryCustomerId": -1,
        "RemoteURL": "false",
        "rate": 600,
        "PublishedId": 7,
        "FileType": "adapt",
        "ID": "7-40",
        "URL": "http://54.201.170.111/assets/001-720p-2500kb.mp4",
        "__metadata": {
            "id": "http://api.internetvideoarchive.com/1.0/DataService/Encodes('7-40')",
            "type": "MediaModel.Encode",
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/Encodes('7-40')"
        },
        "VideoAsset": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/Encodes('7-40')/VideoAsset"
            }
        }    
    }
]
```

