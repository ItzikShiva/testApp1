package absproj.email.api;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public class EmailService {

	public CloseableHttpResponse getEmailValidation(String baseURI, String api_key, String email)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();

		String URI = baseURI + "?api_key=" + api_key + "&email=" + email;

		HttpGet httpGet = new HttpGet(URI);

		CloseableHttpResponse response = httpClient.execute(httpGet);
//		generateEmailResponse(response);
		return response;
	}

//	private EmailResponse emailResponse=null;
//	
//	public EmailService() {
//	super();
//	
//}
//	public EmailResponse getEmailResponse() {
//		return emailResponse;
//	}

//	public void generateEmailResponse(CloseableHttpResponse response) throws ClientProtocolException, IOException {
//		Gson gson = new Gson();
//		
//		HttpEntity entityResponse = response.getEntity();
//		String jsonStringResponse = EntityUtils.toString(entityResponse);
//
//		this.emailResponse = gson.fromJson(jsonStringResponse, EmailResponse.class);
//	}

}
