package API.petStore.store;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class StoreService {
	
	public CloseableHttpResponse getInventory()
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("https://petstore.swagger.io/v2/store/inventory");

		CloseableHttpResponse response = httpClient.execute(httpGet);
//		response.close();
//		httpClient.close();	
		return response;
	}
}
