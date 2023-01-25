package app;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public class BaseTest {
	public static Gson gson = new Gson();

	public static <T> T httpResponseToObj(CloseableHttpResponse response, Class<T> TClass)
			throws ParseException, IOException {
		HttpEntity entityResponse = response.getEntity();
		String jsonStringResponse = EntityUtils.toString(entityResponse);
		return gson.fromJson(jsonStringResponse, TClass);
	}

	public static String httpResponseToString(CloseableHttpResponse response) throws ParseException, IOException {
		HttpEntity entityResponse = response.getEntity();
		String jsonStringResponse = EntityUtils.toString(entityResponse);
		return jsonStringResponse;
	}

	public static boolean checkFileExist(String path) {
		File f = new File(path);
		if (f.exists() && !f.isDirectory()) {
			return true;
		}
		return false;
	}
}