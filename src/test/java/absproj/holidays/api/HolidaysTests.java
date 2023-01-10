package absproj.holidays.api;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import UI.petStore.user.DashboardPage;
import UI.petStore.user.LoginPage;
import UI.petStore.user.UserService;
import UI.petStore.user.UserTests;
import absproj.email.api.EmailErrorResponse;
import absproj.email.api.EmailResponse;

public class HolidaysTests extends HolidaysUtils {
	private static final Logger logger = LogManager.getLogger(HolidaysTests.class);

	/*
	 * 1. validationHolidaysTest 200 
	 * +invalid api_key 
	 * +2. out of range params - 400 3.
	 * invalid params(string instead int) - 400 4. no month,day (maybe year -
	 * "code": "payment_required", ) status 402 5. no country code - "code": status
	 * 400 "payment_required", "details": { "country": [ "This field is required."
	 * ], "month": [ "Ensure this value is less than or equal to 12." ] }
	 * 
	 */

	@Test
	public static void holidaysValidTest() throws InterruptedException, ClientProtocolException, IOException {

		CloseableHttpResponse httpResponse = holidaysService.getHolidays(holidaysParams.basgeURI,
				holidaysParams.valid_api_key, holidaysParams.valid_country, holidaysParams.valid_year,
				holidaysParams.valid_month, holidaysParams.valid_day);
		assertEquals(httpResponse.getStatusLine().getStatusCode(), 200);
		logger.info("got valid email response from server");

		List<HolidaysResponse> holidaysResponse = (List<HolidaysResponse>) httpResponseToArrayObj(httpResponse);
		assertTrue(holidaysResponse != null);
		logger.info("got valid Holidays Array");
	}

	@Test
	public static void invalidApiKeyTest() throws ClientProtocolException, IOException  {
		CloseableHttpResponse httpResponse = holidaysService.getHolidays(holidaysParams.basgeURI,
				holidaysParams.inValid_api_key, holidaysParams.valid_country, holidaysParams.valid_year,
				holidaysParams.valid_month, holidaysParams.valid_day);
		assertEquals(httpResponse.getStatusLine().getStatusCode(), 401);
		logger.info("got invalidApiKeyTest - success");

	}
	
	@Test
	public static void outOfRangeParametersTest() throws ClientProtocolException, IOException {
	

	}

	public static String httpResponseToString(CloseableHttpResponse response) throws ParseException, IOException {
		HttpEntity entityResponse = response.getEntity();
		String jsonStringResponse = EntityUtils.toString(entityResponse);
		return jsonStringResponse;

	}

	public static <T> T httpResponseToArrayObj(CloseableHttpResponse response) throws ParseException, IOException {
		HttpEntity entityResponse = response.getEntity();
		String jsonStringResponse = EntityUtils.toString(entityResponse);

		return gson.fromJson(jsonStringResponse, new TypeToken<List<T>>() {
		}.getType());

	}

}

//logger.info("Car2: " + car2);
//logger.warn("Warning accrued at " + LocalDateTime.now());
//logger.error("Error accrued at " + LocalDateTime.now());
//logger.fatal("Serious problem with car Login in accrued at " + LocalDateTime.now());
