package API.petStore;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import API.petStore.pet.Category;
import API.petStore.pet.CreatePetRequest;
import API.petStore.pet.CreatePetResponse;
import API.petStore.pet.PetService;
import API.petStore.user.CreateUserRequest;
import API.petStore.user.GetUserResponse;
import API.petStore.user.UserService;

public class UserTests {

	@Test
	public static void createUser() throws ClientProtocolException, IOException, URISyntaxException {
		UserService userService = new UserService();

		CreateUserRequest request = new CreateUserRequest(2,"userNameTest","itzik","vaknin","123@test.com","1234","054-66666",4);
		CloseableHttpResponse response = userService.createUser(request);
		Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
		
		Assert.assertEquals("userNameTest" , userService.getUserByUsername("userNameTest").getUsername());
		//HOD - is it ok i assign new value to same var?
		response = userService.login("userNameTest", "1234");
		Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
		response = userService.deleteUser("userNameTest");
		Assert.assertEquals(response.getStatusLine().getStatusCode(),200);

	}
}
