package API.facebook;

import com.google.gson.Gson;

import API.facebook.user.UserService;

public class BaseTest {
	static Gson gson = new Gson();
	static AppParams appParams = new AppParams();
	static UserService userService = new UserService();
}