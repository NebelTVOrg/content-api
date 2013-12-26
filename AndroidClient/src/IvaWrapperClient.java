import com.nebeltv.ivawrapper.Wrapper;
import com.nebeltv.ivawrapper.WrapperTypes;
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
	 * @throws java.lang.Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(getMediaItem(1));
		System.out.println(getMedias(2, 5, "0", null, null));
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

	public static String getMediaItem(Integer id) throws Exception {
		return Wrapper.getWrapper(WrapperTypes.LIVE).getMediaItem(id);
	}

	public static String getMedias(Integer n, Integer skip, String category, String viewType, String viewTypePeriod) throws Exception {
		return Wrapper.getWrapper(WrapperTypes.LIVE).getMedias(n, skip, category, viewType, viewTypePeriod);
	}

	@Deprecated
	public static String getString(String url) throws Exception {
		return Request.Get(fixURL(url)).execute().returnContent().asString();
	}

	@Deprecated
	public static String getMediaItemUsingWebService(Integer id) {
		if (id != null) {
			try {
				String item = getString(MEDIA_ITEM_SERVICE + id.toString());
				System.out.println("item: " + item);
				return item;
			} catch (Exception ex) {
				Logger.getLogger(IvaWrapperClient.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return null;
	}

}
