package API.petStore.user;

import java.util.ArrayList;

import API.petStore.Tag;

public class CreateUserResponse {
	

	User category;
	ArrayList < String > photoUrls = new ArrayList < String > ();
	ArrayList < Tag > tags = new ArrayList < Tag > ();
	private String status;
	private int id;
	
	
	public int getId() {
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
	public User getCategory() {
		return category;
	}
	public void setCategory(User category) {
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
	private String name;


}

