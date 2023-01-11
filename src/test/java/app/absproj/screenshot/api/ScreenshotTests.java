package app.absproj.screenshot.api;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import absproj.email.api.ErrorResponse;

public class ScreenshotTests extends ScreenshotUtils {
	private static final Logger logger = LogManager.getLogger(ScreenshotTests.class);

	@Test
	public static void validnTest() throws InterruptedException, ClientProtocolException, IOException {

		CloseableHttpResponse httpResponse = screenshotService.getScreenshot(screenshotParams.basgeURI,
				screenshotParams.valid_api_key, screenshotParams.valid_url);
		assertEquals(httpResponse.getStatusLine().getStatusCode(), 200);
		logger.info("got valid image response from server");

		// TODO - ask hod about assert cases
		HttpEntity entity = httpResponse.getEntity();
		assertTrue(entity != null);

		assertTrue(httpResponse.getFirstHeader("Content-Type").getValue().equals("image/png"));
	}

	// TODO - ask, about test name. emaiValidationWrongApiKeyTest
	@Test
	public static void invalidApiKeyTest() throws ClientProtocolException, IOException {
		CloseableHttpResponse httpResponse = screenshotService.getScreenshot(screenshotParams.basgeURI,
				screenshotParams.inValid_api_key, screenshotParams.valid_url);
		assertEquals(httpResponse.getStatusLine().getStatusCode(), 401);
		logger.info("got invalidApiKeyTest - success");
	}

	// TODO - i know "invalidUrlTest_1" it's not a good name, to talk with HOD
	@Test
	public static void invalidUrlTest() throws ClientProtocolException, IOException {
		CloseableHttpResponse httpResponse = screenshotService.getScreenshot(screenshotParams.basgeURI,
				screenshotParams.valid_api_key, screenshotParams.inValid_url);
		assertEquals(httpResponse.getStatusLine().getStatusCode(), 400);
		logger.info("got invalidUrlTest - success");

		ErrorResponse errorResponse = httpResponseToObj(httpResponse, ErrorResponse.class);

		assertEquals(errorResponse.getError().getDetails().getUrl().get(0), "Enter a valid URL.");
		logger.info("got \"Enter a valid URL.\" success result");
	}

	@Test
	public static void pageErrorTest() throws ClientProtocolException, IOException {
		CloseableHttpResponse httpResponse = screenshotService.getScreenshot(screenshotParams.basgeURI,
				screenshotParams.valid_api_key, screenshotParams.inValid_url_page);
		assertEquals(httpResponse.getStatusLine().getStatusCode(), 400);
		logger.info("got pageErrorTest - success");

		ErrorResponse errorResponse = httpResponseToObj(httpResponse, ErrorResponse.class);

		assertTrue(errorResponse.getError().getDetails().getError() != null);
		logger.info("got \"Reached error page\" success result");
	}

	@Test
	public static void emptyUrlTest() throws ClientProtocolException, IOException {
		CloseableHttpResponse httpResponse = screenshotService.getScreenshot(screenshotParams.basgeURI,
				screenshotParams.valid_api_key, screenshotParams.empty_url);
		assertEquals(httpResponse.getStatusLine().getStatusCode(), 400);
		logger.info("got emaptyEmailTest - success");

		ErrorResponse emailErrorResponse = httpResponseToObj(httpResponse, ErrorResponse.class);
		assertEquals(emailErrorResponse.getError().getDetails().getUrl().get(0), "This field may not be blank.");
		logger.info("got \"This field may not be blank.\" massage - success");
	}

	// TODO - ask about this method.
	public static <T> T httpResponseToObj(CloseableHttpResponse response, Class<T> TClass)
			throws ParseException, IOException {
		HttpEntity entityResponse = response.getEntity();
		String jsonStringResponse = EntityUtils.toString(entityResponse);
		return gson.fromJson(jsonStringResponse, TClass);
	}

}
