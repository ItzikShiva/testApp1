package API.facebook.user;



public class GetAccessTokenResponse {
	
	   private String access_token= "";
	   private String token_type="";
	   private double expires_in= 5179205;
	   
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public double getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(double expires_in) {
		this.expires_in = expires_in;
	}
	
	   
}
