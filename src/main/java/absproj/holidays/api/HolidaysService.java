package absproj.holidays.api;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class HolidaysService {
	private static Gson gson = new Gson();
	
	public CloseableHttpResponse getHolidays(String baseURI, String api_key, String country, String year, String month,
			String day) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();

		String URI = baseURI + "?api_key=" + api_key + "&country=" + country + "&year=" + year + "&month=" + month
				+ "&day=" + day;

		HttpGet httpGet = new HttpGet(URI);

		CloseableHttpResponse response = httpClient.execute(httpGet);

		
		
	
		
		return response;
	}



}
