package app.petstore.api;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import API.petStore.store.GetInventoryResponse;
import API.petStore.store.StoreService;

public class StoreTests {
	@Test
	public static void getInventory() throws ClientProtocolException, IOException, URISyntaxException {
		StoreService storeService = new StoreService();
		Gson gson = new Gson();
		
		CloseableHttpResponse response = storeService.getInventory();
		Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
		
		HttpEntity entityResponse = response.getEntity();
		String stringResponse = EntityUtils.toString(entityResponse);		
		GetInventoryResponse getInventoryResponse = gson.fromJson(stringResponse, GetInventoryResponse.class);
		//this hardcode check, not have to work! - written just for the practice
		Assert.assertEquals(getInventoryResponse.getSold() , 4);

	}
}
