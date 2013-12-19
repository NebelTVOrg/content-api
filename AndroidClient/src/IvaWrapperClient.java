import com.nebeltv.commons.MediaItem;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.fluent.Request;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dtv
 */
public class IvaWrapperClient {

	private static final String MEDIA_ITEM_SERVICE = "http://dstworks.com:8080/IvaWrapperWeb/getMediaItem?n=";

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		getMediaItem(1);
	}

	/**
	 * @param url
	 * @return
	 */
	public static String fixURL(String url) {
		StringBuilder fixedURL = new StringBuilder();
		String[] urlParts = url.split("\\?");
		fixedURL.append(urlParts[0]).append("?");

		if (urlParts.length > 1) {
			String[] pairs = urlParts[1].split("&");
			for (String pair : pairs) {
				String[] nameValuePair = pair.split("=");
				fixedURL.append("&")
								.append(nameValuePair[0])
								.append("=")
								.append(URLEncoder.encode(nameValuePair[1]));
			}
		}
		return fixedURL.toString();
	}

	public static String getString(String url) throws Exception {
		return Request.Get(fixURL(url)).execute().returnContent().asString();
	}

	public static MediaItem getMediaItem(Integer id) {
		if (id == null) {
			return null;
		}
		try {
			String item = getString(MEDIA_ITEM_SERVICE + id.toString());
			System.out.println("item: " + item);
			return new MediaItem();
		} catch (Exception ex) {
			Logger.getLogger(IvaWrapperClient.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

}
