package app.petstore.api;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.Reporter;

import com.google.gson.Gson;

import API.petStore.Tag;
import API.petStore.pet.Category;
import API.petStore.pet.CreatePetRequest;
import API.petStore.pet.PetService;
import API.petStore.pet.UpdatePetResponse;


public class UpdatePetTests {

	
	
	@Test
	public static void uploadPetImageByID() throws ClientProtocolException, IOException, URISyntaxException {
//ASK - the is no way to know if you success only by response (it's not really updating)
		//also ask about the body form-data in postman (what is additionalMetadata)
		//you can give any id!
		
		PetService petService = new PetService();


		CloseableHttpResponse response = petService.uploadPetImageByID(9999,"C:/Users/itzik vaknin/Downloads/‏‏check.jpg");
		Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
		
	}	
	@Test
	public static void updatePet() throws ClientProtocolException, IOException, URISyntaxException {
//		UpdatePetService updatePetService = new UpdatePetService();
		PetService petService = new PetService();
		ArrayList<String> photoUrls = new ArrayList<String>();
		ArrayList<Tag> tags = new ArrayList<Tag>();
		Gson gson = new Gson();

		photoUrls.add("photoUrlTestUpdate");
		photoUrls.add("photoUrl22");
		photoUrls.add("photoUrl33");
		
		tags.add(new Tag(66, "shiva"));
		tags.add(new Tag(77, "dsff"));
		
		CreatePetRequest request = new CreatePetRequest(new Category(99,"ss"), photoUrls, tags, "availble", 9999, "love");
		CloseableHttpResponse response = petService.updatePet(request);
		Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
		
		HttpEntity entityResponse = response.getEntity();
		String stringResponse = EntityUtils.toString(entityResponse);		
		UpdatePetResponse updatePetResponse = gson.fromJson(stringResponse, UpdatePetResponse.class);
		Assert.assertEquals(updatePetResponse.getId() , petService.getPetByID(9999).getId());
		
		Reporter.log("update pet success");
	}
}
