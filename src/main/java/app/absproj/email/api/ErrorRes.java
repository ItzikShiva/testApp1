package app.absproj.email.api;

public class ErrorRes {
	private String message = null;
	private String code = null;
	private Details details = null;

	public String getMessage() {
		return message;
	}

	public String getCode() {
		return code;
	}

	public Details getDetails() {
		return details;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

}
