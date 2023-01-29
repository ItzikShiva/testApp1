package app.facebook.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import app.facebook.user.api.Field;
import app.facebook.user.api.GetAccessTokenResponse;
import app.facebook.user.api.GetMetadataResponse;
import app.facebook.user.api.UserService;

public class UserTests extends FacebookBaseTest {
		
	@Test
	public static void login()
			throws ClientProtocolException, IOException, URISyntaxException, NoSuchAlgorithmException {
		
		// TODO - how can i do better reading?
		CloseableHttpResponse response = userService.getAccessToken(appParams.getClientId(),
				appParams.getRedirect_uri(), appParams.getResponse_type(), appParams.getClient_secret(),
				appParams.getCODE());

		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		
		HttpEntity entityResponse = response.getEntity();
		GetAccessTokenResponse getAccessTokenResponse = gson.fromJson(EntityUtils.toString(entityResponse),
				GetAccessTokenResponse.class);

		appParams.setACCESS_TOKEN(getAccessTokenResponse.getAccess_token());
	}

	@Test
	public static void metadata() throws ClientProtocolException, IOException, URISyntaxException, InvalidKeyException,
			NoSuchAlgorithmException {
		
		GetMetadataResponse getMetadataResponse = userService.getMetadataBody(appParams.getACCESS_TOKEN());
		Assert.assertNotNull(getMetadataResponse);
		ArrayList<Field> fieldsList = getMetadataResponse.getMetadata().getFields();

		putFieldsInFile(fieldsList, appParams.getField_path());
		//i can return true if is was created

	}

	private static void putFieldsInFile(ArrayList<Field> fields, String path) throws FileNotFoundException {
		File file = new File(path);
		if (file.exists()) {
			Scanner sc = new Scanner(file);
			int i = 0;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (!line.equals(fields.get(i).toString())) {
					createFieldFile(fields, path);
					break;
				}
				i++;
			}
		} else {
			createFieldFile(fields, path);
		}
	}

	private static void createFieldFile(ArrayList<Field> fields, String path) throws FileNotFoundException {
		File file = new File(path);
		PrintWriter pw = new PrintWriter(file);

		for (int i = 0; i < fields.size(); i++) {
			pw.println(fields.get(i).toString());
			System.out.println(fields.get(i).toString());
		}
		pw.close();
	}

}
