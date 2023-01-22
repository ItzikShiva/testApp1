package app.petstore.user.api;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class UserService {

	public GetUserResponse getUserByUsername(String username)
			throws ClientProtocolException, IOException, URISyntaxException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		Gson gson = new Gson();

		String baseURI = "https://petstore.swagger.io/v2/user/" + username;
		HttpGet httpGet = new HttpGet(baseURI);

		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity entityResponse = response.getEntity();
		String jsonStringResponse = EntityUtils.toString(entityResponse);
		GetUserResponse getUserResponse = gson.fromJson(jsonStringResponse, GetUserResponse.class);

//		response.close();
//		httpClient.close();	
		return getUserResponse;
	}

	public CloseableHttpResponse createUser(CreateUserRequest request) throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("https://petstore.swagger.io/v2/user");
		httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
		Gson gson = new Gson();
		String jsonRequest = gson.toJson(request);
		StringEntity entityRequest = new StringEntity(jsonRequest);
		httpPost.setEntity(entityRequest);

		CloseableHttpResponse response = httpClient.execute(httpPost);

//		response.close();
//		httpClient.close();	
		return response;
	}

	public CloseableHttpResponse login(String username, String password)
			throws ClientProtocolException, IOException, URISyntaxException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		Gson gson = new Gson();
		HttpGet httpGet = new HttpGet("https://petstore.swagger.io/v2/user/login/");

		URI uri = new URIBuilder(httpGet.getURI()).addParameter("username", username).addParameter("password", password)
				.build();
		httpGet.setURI(uri);
		CloseableHttpResponse response = httpClient.execute(httpGet);

//	response.close();
//	httpClient.close();	
		return response;
	}
	
	public CloseableHttpResponse deleteUser(String username)
			throws ClientProtocolException, IOException, URISyntaxException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		Gson gson = new Gson();

		String baseURI = "https://petstore.swagger.io/v2/user/" + username;
		HttpDelete httpDelete = new HttpDelete(baseURI);

		//HOD - is it better syntax?
		return httpClient.execute(httpDelete);
//		CloseableHttpResponse response = httpClient.execute(httpDelete);

//	response.close();
//	httpClient.close();	
//		return response;
	}
}
