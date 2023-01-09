package absproj.email.api;

import com.google.gson.Gson;

import absproj.email.api.EmailService;

public class EmailUtils {
	static Gson gson = new Gson();
	static EmailParams emailParams = new EmailParams();
	static EmailService emailService = new EmailService();
}
