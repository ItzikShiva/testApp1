package API.petStore;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.groovy.json.internal.Value;
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
import API.petStore.pet.CreatePetResponse;
import API.petStore.pet.PetService;


public class CreatePetTests {

	@Test
	public static void createPet() throws ClientProtocolException, IOException, URISyntaxException {
		PetService petService = new PetService();
		ArrayList<Tag> tags = new ArrayList<Tag>();
		ArrayList<String> photoUrls = new ArrayList<String>();
		Gson gson = new Gson();

		photoUrls.add("photoUrl");
		photoUrls.add("photoUrl22");
		photoUrls.add("photoUrl33");
		
		tags.add(new Tag(66, "shiva"));
		tags.add(new Tag(77, "dsff"));
		CreatePetRequest request = new CreatePetRequest(new Category(99,"ss"), photoUrls, tags, "availble", 9999, "love");
		CloseableHttpResponse response = petService.createPet(request);
		Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
		
		HttpEntity entityResponse = response.getEntity();
		String stringResponse = EntityUtils.toString(entityResponse);		
		CreatePetResponse createPetResponse = gson.fromJson(stringResponse, CreatePetResponse.class);
		Assert.assertEquals(createPetResponse.getId() , petService.getPetByID(9999).getId());
	}
	
	@Test
	public static void createPetWithoutID() throws ClientProtocolException, IOException, URISyntaxException {
		PetService petService = new PetService();
		ArrayList<String> photoUrls = new ArrayList<String>();
		ArrayList<Tag> tags = new ArrayList<Tag>();
		Gson gson = new Gson();

		photoUrls.add("photoUrl");
		photoUrls.add("photoUrl22");
		photoUrls.add("photoUrl33");
		
		tags.add(new Tag(66, "shiva"));
		tags.add(new Tag(77, "dsff"));
		CreatePetRequest request = new CreatePetRequest();
		request.setName("tempName");
		request.setCategory(new Category(99,"ss"));
		request.setPhotoUrls(photoUrls);
		request.setTags(tags);
		request.setStatus("available");
		
		CloseableHttpResponse response = petService.createPet(request);
		Assert.assertEquals(response.getStatusLine().getStatusCode() , 400);

		//ASK - if it failed in one assert, it continues to the next? seems no
		HttpEntity entityResponse = response.getEntity();
		String stringResponse = EntityUtils.toString(entityResponse);		
		CreatePetResponse createPetResponse = gson.fromJson(stringResponse, CreatePetResponse.class);

		Assert.assertEquals(createPetResponse.getId() , petService.getPetByID(9999).getId());

	}
	
	@Test
	public static void createPetWithDuplicateID() throws ClientProtocolException, IOException, URISyntaxException {
		PetService petService = new PetService();
		ArrayList<Tag> tags = new ArrayList<Tag>();
		ArrayList<String> photoUrls = new ArrayList<String>();
		
		photoUrls.add("photoUrl");
		photoUrls.add("photoUrl22");
		photoUrls.add("photoUrl33");
		
		tags.add(new Tag(66, "shiva"));
		tags.add(new Tag(77, "dsff"));
		CreatePetRequest request = new CreatePetRequest(new Category(99,"ss"), photoUrls, tags, "availble", 9999, "love");
		CloseableHttpResponse response = petService.createPet(request);
		Assert.assertEquals(response.getStatusLine().getStatusCode() , 200);
		//ASK - HOD is it ok to assign new value to same var?
		response = petService.createPet(request);
		Assert.assertEquals(response.getStatusLine().getStatusCode() , 409);
	}
	
	@Test
	public static void createPetWithEmptyObject() throws ClientProtocolException, IOException, URISyntaxException {
		PetService petService = new PetService();
		ArrayList<Tag> tags = new ArrayList<Tag>();
		ArrayList<String> photoUrls = new ArrayList<String>();
		
		photoUrls.add("photoUrl");
		photoUrls.add("photoUrl22");
		photoUrls.add("photoUrl33");
		
		tags.add(new Tag(66, "shiva"));
		tags.add(new Tag(77, "dsff"));
		CreatePetRequest request = new CreatePetRequest();
		
		CloseableHttpResponse response = petService.createPet(request);
		
//		Reporter.log("created Pet With Empty Object");
		Assert.assertEquals(response.getStatusLine().getStatusCode() , 400);
	}
	
	@Test
	public static void createPetWithUnsupportedMediaType() throws ClientProtocolException, IOException, URISyntaxException {
		PetService petService = new PetService();
		ArrayList<Tag> tags = new ArrayList<Tag>();
		ArrayList<String> photoUrls = new ArrayList<String>();
		
		photoUrls.add("photoUrl");
		photoUrls.add("photoUrl22");
		photoUrls.add("photoUrl33");
		
		tags.add(new Tag(66, "shiva"));
		tags.add(new Tag(77, "dsff"));
		CreatePetRequest request = new CreatePetRequest();
		
		CloseableHttpResponse response = petService.createPetWithUnsupportedMediaType(request);
		Reporter.log("created Pet With Empty Object");
		Assert.assertEquals(response.getStatusLine().getStatusCode() , 415);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase() , "Unsupported Media Type");
	}
	
//	CreatePetRequest without mandatory params - you can send empty object, it generates ID, same like createPetWithoutID(). so i didn't do again
//	create with duplicate ID (409) - DONE
//	Create with Empty object (400) - DONE
//	Empty in CreatePet 415 Unsupported Media Type - DONE
	
}
