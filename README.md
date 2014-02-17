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
    "OfficialSiteUrl": "",
    "MediaType": {
        "__deferred": {
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(2)/MediaType"
        }
    },
    "MovieMpaaId": 3,
    "MovieMpaa": {
        "__deferred": {
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(2)/MovieMpaa"
        }
    },
    "TitleAkas": {
        "__deferred": {
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(2)/TitleAkas"
        }
    },
    "Poster": {
        "PosterUrl_172": "http://content.internetvideoarchive.com/content/posters/005/223_562.jpg",
        "PublishedId": 2,
        "PosterUrl_125": "http://content.internetvideoarchive.com/content/posters/005/223_628.jpg",
        "DateAdded": "/Date(1314109320000)/",
        "EntertainmentProgram": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/Posters(2)/EntertainmentProgram"
            }
        },
        "__metadata": {
            "id": "http://api.internetvideoarchive.com/1.0/DataService/Posters(2)",
            "type": "MediaModel.Poster",
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/Posters(2)"
        }
    },
    "HasYouTubeContent": "false",
    "DirectorId": 7699,
    "ExpirationDate": null,
    "DisplayTitle": "On The Town",
    "OkToEncodeAndServe": true,
    "OverallSequence": -1,
    "ProgramToGameFormatMaps": {
        "__deferred": {
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(2)/ProgramToGameFormatMaps"
        }
    },
    "Rank30Day": 1000000,
    "Sequence": -1,
    "NormalizedTitle": "on the town",
    "Director": {
        "Datemodified": "/Date(1315420396000)/",
        "FullName": "Stanley Donen",
        "PerformerScreenCapture": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/Performers(7699)/PerformerScreenCapture"
            }
        },
        "FirstName": "Stanley",
        "ProgramToPerformerMap": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/Performers(7699)/ProgramToPerformerMap"
            }
        },
        "LastName": "Donen",
        "EntertainmentProgramsDirected": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/Performers(7699)/EntertainmentProgramsDirected"
            }
        },
        "__metadata": {
            "id": "http://api.internetvideoarchive.com/1.0/DataService/Performers(7699)",
            "type": "MediaModel.Performer",
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/Performers(7699)"
        },
        "NormalizedName": "stanley donen",
        "PerformerID": 7699,
        "ImagesToTitleOrPerformerMaps": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/Performers(7699)/ImagesToTitleOrPerformerMaps"
            }
        }
    },
    "ReleaseEvents": {
        "__deferred": {
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(2)/ReleaseEvents"
        }
    },
    "PromotesPublishedId": -1,
    "TvRatingId": -1,
    "TvCategoryid": -1,
    "RelatedEntertainmentPrograms": {
        "__deferred": {
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(2)/RelatedEntertainmentPrograms"
        }
    },
    "DateCreated": "/Date(938449560000)/",
    "MusicWarningId": -1,
    "Country": {
        "__deferred": {
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(2)/Country"
        }
    },
    "LanguageId": 0,
    "TvCategory": {
        "__deferred": {
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(2)/TvCategory"
        }
    },
    "GameCategoryId": -1,
    "SongWarning": {
        "__deferred": {
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(2)/SongWarning"
        }
    },
    "SongCategory": {
        "__deferred": {
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(2)/SongCategory"
        }
    },
    "Tagline": "",
    "GameWarningId": -1,
    "Copyrightholder": {
        "__deferred": {
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(2)/Copyrightholder"
        }
    },
    "GameWarning": {
        "__deferred": {
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(2)/GameWarning"
        }
    },
    "SongRiaa": {
        "__deferred": {
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(2)/SongRiaa"
        }
    },
    "Rank1Day": 1000000,
    "IsATitle": false,
    "GameCategory": {
        "__deferred": {
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(2)/GameCategory"
        }
    },
    "MovieCategoryId": 7,
    "MediaReceivedDate": "/Date(1384786152000)/",
    "CountryOforiginId": 0,
    "Publishedid": 2,
    "BoxOfficeInMillions": 0,
    "FirstReleasedYear": 1949,
    "StreamLengthinseconds": 177,
    "ProprietaryCustomerID": -1,
    "MusicAlbumTitle": "",
    "Description": {
        "PublishedId": 2,
        "EntertainmentProgram": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/Descriptions(2)/EntertainmentProgram"
            }
        },
        "__metadata": {
            "id": "http://api.internetvideoarchive.com/1.0/DataService/Descriptions(2)",
            "type": "MediaModel.Description",
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/Descriptions(2)"
        },
        "VideoAsset": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/Descriptions(2)/VideoAsset"
            }
        },
        "ItemDescription": "Three sailors team up to find the beautiful poster girl whose picture they saw in a New York City subway. Oscar-winning score by Bernstein, Comden and Green!"
    },
    "VideoAssets": {
        "__deferred": {
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(2)/VideoAssets"
        }
    },
    "MusicRiaaId": -1,
    "MovieCategory": {
        "__deferred": {
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(2)/MovieCategory"
        }
    },
    "MediaId": 0,
    "RankAllTime": 11666,
    "Rank7Day": 1000000,
    "Title": "ON THE TOWN",
    "VideoAssetScreenCapture": {
        "__deferred": {
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(2)/VideoAssetScreenCapture"
        }
    },
    "MusicCategoryId": -1,
    "ImageGalleryMaps": {
        "__deferred": {
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(2)/ImageGalleryMaps"
        }
    },
    "HasIvaContent": "true",
    "CopyrightholderId": 66,
    "DateModified": "/Date(1391437440000)/",
    "ProgramToPerformerMaps": {
        "__deferred": {
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(2)/ProgramToPerformerMaps"
        }
    },
    "MovieWarningId": -1,
    "TvRating": {
        "__deferred": {
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(2)/TvRating"
        }
    },
    "Language": {
        "__deferred": {
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(2)/Language"
        }
    },
    "__metadata": {
        "id": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(2)",
        "type": "MediaModel.EntertainmentProgram",
        "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(2)"
    }
}
```
http://54.201.170.111:8080/IvaWrapperWeb/getMedias?skip=100&n=3&category=0
```json
[
    {
        "OfficialSiteUrl": "",
        "MediaType": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(114)/MediaType"
            }
        },
        "MovieMpaaId": 3,
        "MovieMpaa": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(114)/MovieMpaa"
            }
        },
        "TitleAkas": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(114)/TitleAkas"
            }
        },
        "Poster": {
            "PosterUrl_172": "http://content.internetvideoarchive.com/content/posters/004/208_930.jpg",
            "PublishedId": 114,
            "PosterUrl_125": "http://content.internetvideoarchive.com/content/posters/004/208_709.jpg",
            "DateAdded": "/Date(1374149820000)/",
            "EntertainmentProgram": {
                "__deferred": {
                    "uri": "http://api.internetvideoarchive.com/1.0/DataService/Posters(114)/EntertainmentProgram"
                }
            },
            "__metadata": {
                "id": "http://api.internetvideoarchive.com/1.0/DataService/Posters(114)",
                "type": "MediaModel.Poster",
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/Posters(114)"
            }
        },
        "HasYouTubeContent": "false",
        "DirectorId": 2308,
        "ExpirationDate": null,
        "DisplayTitle": "At The Circus",
        "OkToEncodeAndServe": true,
        "OverallSequence": -1,
        "ProgramToGameFormatMaps": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(114)/ProgramToGameFormatMaps"
            }
        },
        "Rank30Day": 1000000,
        "Sequence": -1,
        "NormalizedTitle": "at the circus",
        "Director": {
            "Datemodified": "/Date(1315419482000)/",
            "FullName": "Edward Buzzell",
            "PerformerScreenCapture": {
                "__deferred": {
                    "uri": "http://api.internetvideoarchive.com/1.0/DataService/Performers(2308)/PerformerScreenCapture"
                }
            },
            "FirstName": "Edward",
            "ProgramToPerformerMap": {
                "__deferred": {
                    "uri": "http://api.internetvideoarchive.com/1.0/DataService/Performers(2308)/ProgramToPerformerMap"
                }
            },
            "LastName": "Buzzell",
            "EntertainmentProgramsDirected": {
                "__deferred": {
                    "uri": "http://api.internetvideoarchive.com/1.0/DataService/Performers(2308)/EntertainmentProgramsDirected"
                }
            },
            "__metadata": {
                "id": "http://api.internetvideoarchive.com/1.0/DataService/Performers(2308)",
                "type": "MediaModel.Performer",
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/Performers(2308)"
            },
            "NormalizedName": "edward buzzell",
            "PerformerID": 2308,
            "ImagesToTitleOrPerformerMaps": {
                "__deferred": {
                    "uri": "http://api.internetvideoarchive.com/1.0/DataService/Performers(2308)/ImagesToTitleOrPerformerMaps"
                }
            }
        },
        "ReleaseEvents": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(114)/ReleaseEvents"
            }
        },
        "PromotesPublishedId": -1,
        "TvRatingId": -1,
        "TvCategoryid": -1,
        "RelatedEntertainmentPrograms": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(114)/RelatedEntertainmentPrograms"
            }
        },
        "DateCreated": "/Date(938449260000)/",
        "MusicWarningId": -1,
        "Country": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(114)/Country"
            }
        },
        "LanguageId": 0,
        "TvCategory": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(114)/TvCategory"
            }
        },
        "GameCategoryId": -1,
        "SongWarning": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(114)/SongWarning"
            }
        },
        "SongCategory": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(114)/SongCategory"
            }
        },
        "Tagline": "",
        "GameWarningId": -1,
        "Copyrightholder": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(114)/Copyrightholder"
            }
        },
        "GameWarning": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(114)/GameWarning"
            }
        },
        "SongRiaa": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(114)/SongRiaa"
            }
        },
        "Rank1Day": 1000000,
        "IsATitle": false,
        "GameCategory": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(114)/GameCategory"
            }
        },
        "MovieCategoryId": 3,
        "MediaReceivedDate": null,
        "CountryOforiginId": 0,
        "Publishedid": 114,
        "BoxOfficeInMillions": 0,
        "FirstReleasedYear": 1939,
        "StreamLengthinseconds": 89,
        "ProprietaryCustomerID": -1,
        "MusicAlbumTitle": "",
        "Description": {
            "PublishedId": 114,
            "EntertainmentProgram": {
                "__deferred": {
                    "uri": "http://api.internetvideoarchive.com/1.0/DataService/Descriptions(114)/EntertainmentProgram"
                }
            },
            "__metadata": {
                "id": "http://api.internetvideoarchive.com/1.0/DataService/Descriptions(114)",
                "type": "MediaModel.Description",
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/Descriptions(114)"
            },
            "VideoAsset": {
                "__deferred": {
                    "uri": "http://api.internetvideoarchive.com/1.0/DataService/Descriptions(114)/VideoAsset"
                }
            },
            "ItemDescription": "A circus owner enlists the Marx Brothers' aid in saving his run-down circus from bankruptcy."
        },
        "VideoAssets": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(114)/VideoAssets"
            }
        },
        "MusicRiaaId": -1,
        "MovieCategory": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(114)/MovieCategory"
            }
        },
        "MediaId": 0,
        "RankAllTime": 13696,
        "Rank7Day": 1000000,
        "Title": "AT THE CIRCUS",
        "VideoAssetScreenCapture": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(114)/VideoAssetScreenCapture"
            }
        },
        "MusicCategoryId": -1,
        "ImageGalleryMaps": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(114)/ImageGalleryMaps"
            }
        },
        "HasIvaContent": "true",
        "CopyrightholderId": 66,
        "DateModified": "/Date(1391437860000)/",
        "ProgramToPerformerMaps": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(114)/ProgramToPerformerMaps"
            }
        },
        "MovieWarningId": -1,
        "TvRating": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(114)/TvRating"
            }
        },
        "Language": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(114)/Language"
            }
        },
        "__metadata": {
            "id": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(114)",
            "type": "MediaModel.EntertainmentProgram",
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(114)"
        }
    },
    {
        "OfficialSiteUrl": "",
        "MediaType": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(115)/MediaType"
            }
        },
        "MovieMpaaId": 1,
        "MovieMpaa": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(115)/MovieMpaa"
            }
        },
        "TitleAkas": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(115)/TitleAkas"
            }
        },
        "Poster": {
            "PosterUrl_172": "http://content.internetvideoarchive.com/content/posters/002/85_517.jpg",
            "PublishedId": 115,
            "PosterUrl_125": "http://content.internetvideoarchive.com/content/posters/002/85_931.jpg",
            "DateAdded": "/Date(1314115140000)/",
            "EntertainmentProgram": {
                "__deferred": {
                    "uri": "http://api.internetvideoarchive.com/1.0/DataService/Posters(115)/EntertainmentProgram"
                }
            },
            "__metadata": {
                "id": "http://api.internetvideoarchive.com/1.0/DataService/Posters(115)",
                "type": "MediaModel.Poster",
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/Posters(115)"
            }
        },
        "HasYouTubeContent": "false",
        "DirectorId": 4168,
        "ExpirationDate": null,
        "DisplayTitle": "The Blue Max",
        "OkToEncodeAndServe": true,
        "OverallSequence": -1,
        "ProgramToGameFormatMaps": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(115)/ProgramToGameFormatMaps"
            }
        },
        "Rank30Day": 1000000,
        "Sequence": -1,
        "NormalizedTitle": "blue max",
        "Director": {
            "Datemodified": "/Date(1315421045000)/",
            "FullName": "John Guillermin",
            "PerformerScreenCapture": {
                "__deferred": {
                    "uri": "http://api.internetvideoarchive.com/1.0/DataService/Performers(4168)/PerformerScreenCapture"
                }
            },
            "FirstName": "John",
            "ProgramToPerformerMap": {
                "__deferred": {
                    "uri": "http://api.internetvideoarchive.com/1.0/DataService/Performers(4168)/ProgramToPerformerMap"
                }
            },
            "LastName": "Guillermin",
            "EntertainmentProgramsDirected": {
                "__deferred": {
                    "uri": "http://api.internetvideoarchive.com/1.0/DataService/Performers(4168)/EntertainmentProgramsDirected"
                }
            },
            "__metadata": {
                "id": "http://api.internetvideoarchive.com/1.0/DataService/Performers(4168)",
                "type": "MediaModel.Performer",
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/Performers(4168)"
            },
            "NormalizedName": "john guillermin",
            "PerformerID": 4168,
            "ImagesToTitleOrPerformerMaps": {
                "__deferred": {
                    "uri": "http://api.internetvideoarchive.com/1.0/DataService/Performers(4168)/ImagesToTitleOrPerformerMaps"
                }
            }
        },
        "ReleaseEvents": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(115)/ReleaseEvents"
            }
        },
        "PromotesPublishedId": -1,
        "TvRatingId": -1,
        "TvCategoryid": -1,
        "RelatedEntertainmentPrograms": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(115)/RelatedEntertainmentPrograms"
            }
        },
        "DateCreated": "/Date(938449260000)/",
        "MusicWarningId": -1,
        "Country": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(115)/Country"
            }
        },
        "LanguageId": 0,
        "TvCategory": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(115)/TvCategory"
            }
        },
        "GameCategoryId": -1,
        "SongWarning": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(115)/SongWarning"
            }
        },
        "SongCategory": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(115)/SongCategory"
            }
        },
        "Tagline": "",
        "GameWarningId": -1,
        "Copyrightholder": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(115)/Copyrightholder"
            }
        },
        "GameWarning": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(115)/GameWarning"
            }
        },
        "SongRiaa": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(115)/SongRiaa"
            }
        },
        "Rank1Day": 1000000,
        "IsATitle": false,
        "GameCategory": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(115)/GameCategory"
            }
        },
        "MovieCategoryId": 11,
        "MediaReceivedDate": null,
        "CountryOforiginId": 0,
        "Publishedid": 115,
        "BoxOfficeInMillions": 0,
        "FirstReleasedYear": 1966,
        "StreamLengthinseconds": 8,
        "ProprietaryCustomerID": -1,
        "MusicAlbumTitle": "",
        "Description": {
            "PublishedId": 115,
            "EntertainmentProgram": {
                "__deferred": {
                    "uri": "http://api.internetvideoarchive.com/1.0/DataService/Descriptions(115)/EntertainmentProgram"
                }
            },
            "__metadata": {
                "id": "http://api.internetvideoarchive.com/1.0/DataService/Descriptions(115)",
                "type": "MediaModel.Description",
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/Descriptions(115)"
            },
            "VideoAsset": {
                "__deferred": {
                    "uri": "http://api.internetvideoarchive.com/1.0/DataService/Descriptions(115)/VideoAsset"
                }
            },
            "ItemDescription": "During World War I, a young German aviator competes with other members of his squadron for the coveted Blue Max flying award. Terrific aerial photography!"
        },
        "VideoAssets": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(115)/VideoAssets"
            }
        },
        "MusicRiaaId": -1,
        "MovieCategory": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(115)/MovieCategory"
            }
        },
        "MediaId": 0,
        "RankAllTime": 12909,
        "Rank7Day": 1000000,
        "Title": "BLUE MAX, THE",
        "VideoAssetScreenCapture": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(115)/VideoAssetScreenCapture"
            }
        },
        "MusicCategoryId": -1,
        "ImageGalleryMaps": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(115)/ImageGalleryMaps"
            }
        },
        "HasIvaContent": "true",
        "CopyrightholderId": 34,
        "DateModified": "/Date(1391437260000)/",
        "ProgramToPerformerMaps": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(115)/ProgramToPerformerMaps"
            }
        },
        "MovieWarningId": -1,
        "TvRating": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(115)/TvRating"
            }
        },
        "Language": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(115)/Language"
            }
        },
        "__metadata": {
            "id": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(115)",
            "type": "MediaModel.EntertainmentProgram",
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(115)"
        }
    },
    {
        "OfficialSiteUrl": "",
        "MediaType": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(116)/MediaType"
            }
        },
        "MovieMpaaId": 3,
        "MovieMpaa": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(116)/MovieMpaa"
            }
        },
        "TitleAkas": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(116)/TitleAkas"
            }
        },
        "Poster": {
            "PosterUrl_172": "http://content.internetvideoarchive.com/content/posters/006/253_205.jpg",
            "PublishedId": 116,
            "PosterUrl_125": "http://content.internetvideoarchive.com/content/posters/006/253_620.jpg",
            "DateAdded": "/Date(1314115140000)/",
            "EntertainmentProgram": {
                "__deferred": {
                    "uri": "http://api.internetvideoarchive.com/1.0/DataService/Posters(116)/EntertainmentProgram"
                }
            },
            "__metadata": {
                "id": "http://api.internetvideoarchive.com/1.0/DataService/Posters(116)",
                "type": "MediaModel.Poster",
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/Posters(116)"
            }
        },
        "HasYouTubeContent": "false",
        "DirectorId": 1268,
        "ExpirationDate": null,
        "DisplayTitle": "The Barkleys Of Broadway",
        "OkToEncodeAndServe": true,
        "OverallSequence": -1,
        "ProgramToGameFormatMaps": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(116)/ProgramToGameFormatMaps"
            }
        },
        "Rank30Day": 1000000,
        "Sequence": -1,
        "NormalizedTitle": "barkleys of broadway",
        "Director": {
            "Datemodified": "/Date(1315424100000)/",
            "FullName": "Charles Walters",
            "PerformerScreenCapture": {
                "__deferred": {
                    "uri": "http://api.internetvideoarchive.com/1.0/DataService/Performers(1268)/PerformerScreenCapture"
                }
            },
            "FirstName": "Charles",
            "ProgramToPerformerMap": {
                "__deferred": {
                    "uri": "http://api.internetvideoarchive.com/1.0/DataService/Performers(1268)/ProgramToPerformerMap"
                }
            },
            "LastName": "Walters",
            "EntertainmentProgramsDirected": {
                "__deferred": {
                    "uri": "http://api.internetvideoarchive.com/1.0/DataService/Performers(1268)/EntertainmentProgramsDirected"
                }
            },
            "__metadata": {
                "id": "http://api.internetvideoarchive.com/1.0/DataService/Performers(1268)",
                "type": "MediaModel.Performer",
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/Performers(1268)"
            },
            "NormalizedName": "charles walters",
            "PerformerID": 1268,
            "ImagesToTitleOrPerformerMaps": {
                "__deferred": {
                    "uri": "http://api.internetvideoarchive.com/1.0/DataService/Performers(1268)/ImagesToTitleOrPerformerMaps"
                }
            }
        },
        "ReleaseEvents": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(116)/ReleaseEvents"
            }
        },
        "PromotesPublishedId": -1,
        "TvRatingId": -1,
        "TvCategoryid": -1,
        "RelatedEntertainmentPrograms": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(116)/RelatedEntertainmentPrograms"
            }
        },
        "DateCreated": "/Date(938449260000)/",
        "MusicWarningId": -1,
        "Country": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(116)/Country"
            }
        },
        "LanguageId": 0,
        "TvCategory": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(116)/TvCategory"
            }
        },
        "GameCategoryId": -1,
        "SongWarning": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(116)/SongWarning"
            }
        },
        "SongCategory": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(116)/SongCategory"
            }
        },
        "Tagline": "",
        "GameWarningId": -1,
        "Copyrightholder": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(116)/Copyrightholder"
            }
        },
        "GameWarning": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(116)/GameWarning"
            }
        },
        "SongRiaa": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(116)/SongRiaa"
            }
        },
        "Rank1Day": 1000000,
        "IsATitle": false,
        "GameCategory": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(116)/GameCategory"
            }
        },
        "MovieCategoryId": 7,
        "MediaReceivedDate": null,
        "CountryOforiginId": 0,
        "Publishedid": 116,
        "BoxOfficeInMillions": 0,
        "FirstReleasedYear": 1949,
        "StreamLengthinseconds": 150,
        "ProprietaryCustomerID": -1,
        "MusicAlbumTitle": "",
        "Description": {
            "PublishedId": 116,
            "EntertainmentProgram": {
                "__deferred": {
                    "uri": "http://api.internetvideoarchive.com/1.0/DataService/Descriptions(116)/EntertainmentProgram"
                }
            },
            "__metadata": {
                "id": "http://api.internetvideoarchive.com/1.0/DataService/Descriptions(116)",
                "type": "MediaModel.Description",
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/Descriptions(116)"
            },
            "VideoAsset": {
                "__deferred": {
                    "uri": "http://api.internetvideoarchive.com/1.0/DataService/Descriptions(116)/VideoAsset"
                }
            },
            "ItemDescription": "A theatrical couple temporarily splits up over a disagreement. Astaire and Rogers' last film together!"
        },
        "VideoAssets": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(116)/VideoAssets"
            }
        },
        "MusicRiaaId": -1,
        "MovieCategory": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(116)/MovieCategory"
            }
        },
        "MediaId": 0,
        "RankAllTime": 1000000,
        "Rank7Day": 1000000,
        "Title": "BARKLEYS OF BROADWAY, THE",
        "VideoAssetScreenCapture": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(116)/VideoAssetScreenCapture"
            }
        },
        "MusicCategoryId": -1,
        "ImageGalleryMaps": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(116)/ImageGalleryMaps"
            }
        },
        "HasIvaContent": "true",
        "CopyrightholderId": 66,
        "DateModified": "/Date(1391437140000)/",
        "ProgramToPerformerMaps": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(116)/ProgramToPerformerMaps"
            }
        },
        "MovieWarningId": -1,
        "TvRating": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(116)/TvRating"
            }
        },
        "Language": {
            "__deferred": {
                "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(116)/Language"
            }
        },
        "__metadata": {
            "id": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(116)",
            "type": "MediaModel.EntertainmentProgram",
            "uri": "http://api.internetvideoarchive.com/1.0/DataService/EntertainmentPrograms(116)"
        }
    }
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

