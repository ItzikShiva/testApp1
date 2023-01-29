package app.absproj.screenshot.api;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public class ScreenshotService {

	public CloseableHttpResponse getScreenshot(String baseURI, String api_key, String url)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String URI = baseURI + "?api_key=" + api_key + "&url=" + url;
		HttpGet httpGet = new HttpGet(URI);

		CloseableHttpResponse response = httpClient.execute(httpGet);
		return response;
	}
}
