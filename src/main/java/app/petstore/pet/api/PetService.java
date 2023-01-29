package app.petstore.pet.api;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

//TODO important!!! find another rest client - DONE
//TODO: Update an existing pet PUT API - DONE
///pet/{petId}/uploadImage - DONE
// /pet/{petId} - DONE
//USER:
// Create, Get, Delete, Login
//Store:
///store/inventory

public class PetService {
	public CloseableHttpResponse uploadPetImageByID(int id, String imgPath)
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("https://petstore.swagger.io/v2/pet/" + id + "/uploadImage");

		// Build the form data
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		File file = new File(imgPath);
		builder.addBinaryBody("file", file, ContentType.APPLICATION_OCTET_STREAM, "check.jpg");
		HttpEntity formData = builder.build();
		// Set the form data to the request
		httpPost.setEntity(formData);
		// Send the request and receive the response
		CloseableHttpResponse response = httpClient.execute(httpPost);
//		response.close();
//		httpClient.close();	

		return response;
	}

	public CloseableHttpResponse updatePet(CreatePetRequest request) throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut("https://petstore.swagger.io/v2/pet");
		httpPut.addHeader("Content-Type", "application/json; charset=utf-8");
		Gson gson = new Gson();

		String jsonRequest = gson.toJson(request);
		StringEntity entityRequest = new StringEntity(jsonRequest);
		httpPut.setEntity(entityRequest);

		CloseableHttpResponse response = httpClient.execute(httpPut);

//		response.close();
//		httpClient.close();	

		return response;
	}

	public CloseableHttpResponse createPetWithUnsupportedMediaType(CreatePetRequest request)
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("https://petstore.swagger.io/v2/pet");
//		httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
		Gson gson = new Gson();
		String jsonRequest = gson.toJson(request);
		StringEntity entityRequest = new StringEntity(jsonRequest);
		httpPost.setEntity(entityRequest);

		CloseableHttpResponse response = httpClient.execute(httpPost);

//		response.close();
//		httpClient.close();	

		return response;

	}

	public GetPetResponse getPetByID(int id) throws ClientProtocolException, IOException, URISyntaxException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		Gson gson = new Gson();

		// HOD - it can be also class URI whats better?
//		URI baseURI = new URI("https://petstore.swagger.io/v2/pet/"+ (int)id);
		String baseURI = "https://petstore.swagger.io/v2/pet/" + id;
		HttpGet httpGet = new HttpGet(baseURI);

		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity entityResponse = response.getEntity();
		String jsonStringResponse = EntityUtils.toString(entityResponse);

		GetPetResponse getPetResponse = gson.fromJson(jsonStringResponse, GetPetResponse.class);

//		response.close();
//		httpClient.close();	
		return getPetResponse;
	}

	public CloseableHttpResponse createPet(CreatePetRequest request) throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("https://petstore.swagger.io/v2/pet");
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

}
