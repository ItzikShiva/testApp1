package absproj.email.api;

public class EmailResponse {
	public String email;
	public String autocorrect;
	public String deliverability;
	public String quality_score;
	public IsClass is_catchall_email;
	public IsClass is_disposable_email;
	public IsClass is_free_email;
	public IsClass is_mx_found;
	public IsClass is_role_email;
	public IsClass is_smtp_valid;
	public IsClass is_valid_format;
	public String getEmail() {
		return email;
	}
	public String getAutocorrect() {
		return autocorrect;
	}
	public String getDeliverability() {
		return deliverability;
	}
	public String getQuality_score() {
		return quality_score;
	}
	public IsClass getIs_catchall_email() {
		return is_catchall_email;
	}
	public IsClass getIs_disposable_email() {
		return is_disposable_email;
	}
	public IsClass getIs_free_email() {
		return is_free_email;
	}
	public IsClass getIs_mx_found() {
		return is_mx_found;
	}
	public IsClass getIs_role_email() {
		return is_role_email;
	}
	public IsClass getIs_smtp_valid() {
		return is_smtp_valid;
	}
	public IsClass getIs_valid_format() {
		return is_valid_format;
	}
	


}
