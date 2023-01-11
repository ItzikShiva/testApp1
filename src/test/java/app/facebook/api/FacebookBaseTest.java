package app.facebook.api;

import com.google.gson.Gson;

import API.facebook.user.UserService;

public class FacebookBaseTest {
	static Gson gson = new Gson();
	static AppParams appParams = new AppParams();
	static UserService userService = new UserService();
}