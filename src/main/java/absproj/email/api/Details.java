package absproj.email.api;

import java.util.ArrayList;

public class Details {
//TODO - ask hod if itws ok to do generic response. somtimes with "email" sometimes with "url"
	private ArrayList<String> email = null;
	private ArrayList<String> url = null;
	private String error = null;
	
	
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public ArrayList<String> getUrl() {
		return url;
	}
	public void setUrl(ArrayList<String> url) {
		this.url = url;
	}
	public ArrayList<String> getEmail() {
		return email;
	}
	public void setEmail(ArrayList<String> email) {
		this.email = email;
	}

}
