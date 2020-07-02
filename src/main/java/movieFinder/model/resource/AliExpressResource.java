package movieFinder.model.resource;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import movieFinder.model.aliexpress.AliExpress;


public class AliExpressResource {

	private static final Logger log = Logger.getLogger(AliExpressResource.class.getName());

	private final String api = "33503";
	private final String uri = "http://gw.api.alibaba.com/openapi/param2/2/portals.open/api.listPromotionProduct/";
	
	public AliExpressResource() {
	}


	public AliExpress getProducts(String param) throws UnsupportedEncodingException {
		String res2 = "";
		param.replace(":", "");
		param.replace(",", "");
		param.replace("!", "");
		param.replace("¡", "");
		param.replace("(", "");
		param.replace(")", "");
		String[] p = param.split(" ");
		for(int i=0;p.length>i;i++) {
			if(p[i].length()>4) {
				res2 = p[i];
				break;
			}
		}
		String query = URLEncoder.encode(res2, "UTF-8");
		ClientResource cr = null;
		AliExpress res = null;
		try {
			cr = new ClientResource(uri + api + "?fields=productId,productTitle,productUrl,imageUrl&keywords=" + query);
			res = cr.get(AliExpress.class);
			log.log(Level.FINE, "Búsqueda de productos de " + query + " realizada correctamente.");
		} catch (ResourceException e) {
			log.log(Level.WARNING, "Error al obtener los productos: " + cr.getResponse().getStatus());
			throw e;
		}
		return res;
	}
}
