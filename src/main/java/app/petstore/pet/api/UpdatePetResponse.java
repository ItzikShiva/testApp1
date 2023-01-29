package app.petstore.pet.api;

import java.util.ArrayList;

import app.petstore.api.Tag;

public class UpdatePetResponse {

	Category category;
	ArrayList < String > photoUrls = new ArrayList < String > ();
	ArrayList < Tag > tags = new ArrayList < Tag > ();
	private String status;
	private int id;
	private String name;
	
	public UpdatePetResponse(Category category, ArrayList<String> photoUrls, ArrayList<Tag> tags, String status,
			int id, String name) {
		super();
		this.category = category;
		this.photoUrls = photoUrls;
		this.tags = tags;
		this.status = status;
		this.id = id;
		this.name = name;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public ArrayList<String> getPhotoUrls() {
		return photoUrls;
	}
	public void setPhotoUrls(ArrayList<String> photoUrls) {
		this.photoUrls = photoUrls;
	}
	public ArrayList<Tag> getTags() {
		return tags;
	}
	public void setTags(ArrayList<Tag> tags) {
		this.tags = tags;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public float getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}
