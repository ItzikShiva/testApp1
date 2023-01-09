package absproj.email.api;

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


public class EmailTests extends EmailUtils {
	private static final Logger logger = LogManager.getLogger(EmailTests.class);

	@Test
	public static void emailValidationTest() throws InterruptedException, ClientProtocolException, IOException {

		CloseableHttpResponse httpResponse = emailService.getEmailValidation(emailParams.basgeURI,
				emailParams.valid_api_key, emailParams.valid_email);
		assertEquals(httpResponse.getStatusLine().getStatusCode(), 200);
		logger.info("got valid email response from server");

		EmailResponse emailResponse = httpResponseToObj(httpResponse, EmailResponse.class);
		assertEquals(emailResponse.getDeliverability(), "DELIVERABLE");
		logger.info("got valid DELIVERABLE email");
		assertTrue(emailResponse.getIs_valid_format().getValue());
		logger.info("got valid email format");

		// TODO - show Hod what i try to do with the response from the service, and then
		// i can call once, and use the resposnse.
//		assertTrue(emailService.getEmailResponse().getIs_valid_format().getValue());
//		assertEquals(emailResponse.getIs_valid_format().getValue(), true);

	}

	// TODO - ask, about test name. emaiValidationWrongApiKeyTest
	@Test
	public static void invalidApiKeyTest() throws ClientProtocolException, IOException {
		CloseableHttpResponse httpResponse = emailService.getEmailValidation(emailParams.basgeURI,
				emailParams.inValid_api_key, emailParams.valid_email);
		assertEquals(httpResponse.getStatusLine().getStatusCode(), 401);
		logger.info("got invalidApiKeyTest - success");

	}

	@Test
	public static void invalidEmailTest() throws ClientProtocolException, IOException {
		CloseableHttpResponse httpResponse = emailService.getEmailValidation(emailParams.basgeURI,
				emailParams.valid_api_key, emailParams.inValid_email);
		assertEquals(httpResponse.getStatusLine().getStatusCode(), 200);
		logger.info("got invalidEmailTest - success");

		EmailResponse emailResponse = httpResponseToObj(httpResponse, EmailResponse.class);

		assertEquals(emailResponse.getDeliverability(), "UNDELIVERABLE");
		logger.info("got \"UNDELIVERABLE\" success result");
	}

	@Test
	public static void emaptyEmailTest() throws ClientProtocolException, IOException {
		CloseableHttpResponse httpResponse = emailService.getEmailValidation(emailParams.basgeURI,
				emailParams.valid_api_key, emailParams.emapty_email);
		assertEquals(httpResponse.getStatusLine().getStatusCode(), 400);
		logger.info("got emaptyEmailTest - success");

		ErrorResponse emailErrorResponse = httpResponseToObj(httpResponse, ErrorResponse.class);
		assertEquals(emailErrorResponse.getError().getDetails().getEmail().get(0), "This is a required argument.");
		logger.info("got error massage - success");
	}

	public static String httpResponseToString(CloseableHttpResponse response) throws ParseException, IOException {
		HttpEntity entityResponse = response.getEntity();
		String jsonStringResponse = EntityUtils.toString(entityResponse);
		return jsonStringResponse;

	}

	// TODO - ask about this method.
	public static <T> T httpResponseToObj(CloseableHttpResponse response, Class<T> TClass)
			throws ParseException, IOException {
		HttpEntity entityResponse = response.getEntity();
		String jsonStringResponse = EntityUtils.toString(entityResponse);
		return gson.fromJson(jsonStringResponse, TClass);
	}

}

//logger.info("Car2: " + car2);
//logger.warn("Warning accrued at " + LocalDateTime.now());
//logger.error("Error accrued at " + LocalDateTime.now());
//logger.fatal("Serious problem with car Login in accrued at " + LocalDateTime.now());
