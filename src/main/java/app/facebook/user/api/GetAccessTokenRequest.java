package app.facebook.user.api;

public class GetAccessTokenRequest {
	
	private String client_id = "471577415127450";
	private String redirect_uri="https://task-day.onrender.com/%23/";
	private String response_type = "code";
	private String client_secret = "b5c404be464567af8bb3c71403bd7415";
	private String code="";
	
	
	
	
	public GetAccessTokenRequest(String client_id, String redirect_uri, String response_type, String client_secret,
			String code) {
		super();
		this.client_id = client_id;
		this.redirect_uri = redirect_uri;
		this.response_type = response_type;
		this.client_secret = client_secret;
		this.code = code;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getRedirect_uri() {
		return redirect_uri;
	}
	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}
	public String getResponse_type() {
		return response_type;
	}
	public void setResponse_type(String response_type) {
		this.response_type = response_type;
	}
	public String getClient_secret() {
		return client_secret;
	}
	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	
	
}
