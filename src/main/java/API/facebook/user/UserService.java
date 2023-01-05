package API.facebook.user;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import API.petStore.pet.GetPetResponse;
import API.petStore.user.GetUserResponse;

public class UserService {

	public CloseableHttpResponse getMetadata(String access_token) throws ClientProtocolException, IOException{	
		CloseableHttpClient httpClient = HttpClients.createDefault();
		Gson gson = new Gson();

		String baseURI = "https://graph.facebook.com/v15.0/me?metadata=1&access_token=" + access_token;
		HttpGet httpGet = new HttpGet(baseURI);

		CloseableHttpResponse response = httpClient.execute(httpGet);
		
		return response;
	}
	
	public GetMetadataResponse getMetadataBody(String access_token) throws ClientProtocolException, IOException  {
		Gson gson = new Gson();
		CloseableHttpResponse response = getMetadata(access_token);
		HttpEntity entityResponse = response.getEntity();
		String jsonStringResponse = EntityUtils.toString(entityResponse);

		GetMetadataResponse getMetadataResponse = gson.fromJson(jsonStringResponse, GetMetadataResponse.class);
		return getMetadataResponse;
	}
	
	public GetAccessTokenResponse getAccessTokenBody(String client_id, String redirect_uri, String response_type,
			String client_secret, String code) throws ClientProtocolException, IOException, URISyntaxException {
		Gson gson = new Gson();
		CloseableHttpResponse response = getAccessToken(client_id, redirect_uri, response_type, client_secret, code);

		HttpEntity entityResponse = response.getEntity();
		String jsonStringResponse = EntityUtils.toString(entityResponse);

		GetAccessTokenResponse getAccessTokenResponse = gson.fromJson(jsonStringResponse, GetAccessTokenResponse.class);
		return getAccessTokenResponse;
	}

	public CloseableHttpResponse getAccessToken(String client_id, String redirect_uri, String response_type,
			String client_secret, String code) throws ClientProtocolException, IOException, URISyntaxException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost();

		URIBuilder builder = new URIBuilder("https://graph.facebook.com/v3.3/oauth/access_token");
		builder.setParameter("client_id", client_id).setParameter("redirect_uri", "https://task-day.onrender.com/#/")
				.setParameter("response_type", response_type).setParameter("client_secret", client_secret)
				.setParameter("code", code);
		httpPost.setURI(builder.build());
		
		CloseableHttpResponse response = httpClient.execute(httpPost);
		return response;
	}
}
