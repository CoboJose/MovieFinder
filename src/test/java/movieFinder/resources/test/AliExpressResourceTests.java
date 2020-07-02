package movieFinder.resources.test;

import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import movieFinder.model.aliexpress.AliExpress;
import movieFinder.model.resource.AliExpressResource;

public class AliExpressResourceTests {
	AliExpressResource aliexpress = new AliExpressResource();
	
	@Test
	public void testGetProducts() throws UnsupportedEncodingException {
		String busqueda = "EndGame";
		System.out.println("Test AliExpress: Probando búsqueda de productos de " + busqueda);
		AliExpress res = aliexpress.getProducts(busqueda);
		
		// La siguiente línea es para traducir cuando no hay resultados
		if (res.toString().contains("3444d69d")) {
			res = null;
		}
		if (res != null) {
			System.out.println("Encontrado correctamente");
		}

		assertNotNull("La lista de productos no puede ser null", res);

	}
}