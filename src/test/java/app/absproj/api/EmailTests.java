package app.absproj.api;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.annotations.Test;

import app.absproj.email.api.EmailResponse;

public class EmailTests extends EmailBaseTest {
	private static final Logger logger = LogManager.getLogger(EmailTests.class);
	private static String valid_email = "itzikv3@gmail.com";

	@Test
	public static void emailValidationTest() throws InterruptedException, ClientProtocolException, IOException {
		CloseableHttpResponse httpResponse = emailService.getEmailValidation(valid_email);
		assertEquals(httpResponse.getStatusLine().getStatusCode(), 200);
		logger.info("got 200 response from server");

		EmailResponse emailResponse = httpResponseToObj(httpResponse, EmailResponse.class);
		//HOD - i can put line 28 inside next func as parameter. but will be less readability, what is your opinion?
		emailValidationTestAssertions(emailResponse);
	}

	private static void emailValidationTestAssertions(EmailResponse emailResponse) {
		assertEquals(emailResponse.getEmail(), valid_email);
		logger.info("got valid email");
		assertEquals(emailResponse.getDeliverability(), "DELIVERABLE");
		logger.info("got valid DELIVERABLE email");
		assertEquals(emailResponse.getQuality_score(), "0.70");
		logger.info("got valid email quality_score: 0.70");
		assertTrue(emailResponse.getIs_valid_format().getValue());
		logger.info("got valid email format");
		assertTrue(emailResponse.getIs_free_email().getValue());
		logger.info("got valid \"is_free_email: \"true response");
		assertEquals(emailResponse.getIs_disposable_email().getValue(), false);
		logger.info("got valid \"is_disposable_email\": false ");
		assertEquals(emailResponse.getIs_role_email().getValue(), false);
		logger.info("got valid \"is_role_email\": false ");
		assertEquals(emailResponse.getIs_catchall_email().getValue(), false);
		logger.info("got valid \"is_catchall_email\": false ");
		assertEquals(emailResponse.getAutocorrect(), "");
		logger.info("got valid autocorrect \"\" ");
		assertTrue(emailResponse.getIs_mx_found().getValue());
		logger.info("got valid is_mx_found");
		assertTrue(emailResponse.getIs_smtp_valid().getValue());
		logger.info("got valid is_smtp_valid");
	}
}

//logger.info("Car2: " + car2);
//logger.warn("Warning accrued at " + LocalDateTime.now());
//logger.error("Error accrued at " + LocalDateTime.now());
//logger.fatal("Serious problem with car Login in accrued at " + LocalDateTime.now());
