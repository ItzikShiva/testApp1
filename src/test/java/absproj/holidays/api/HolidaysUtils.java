package absproj.holidays.api;

import com.google.gson.Gson;

import absproj.email.api.EmailService;

public class HolidaysUtils {
	static Gson gson = new Gson();
	static HolidaysParams holidaysParams = new HolidaysParams();
	static HolidaysService holidaysService = new HolidaysService();
}
