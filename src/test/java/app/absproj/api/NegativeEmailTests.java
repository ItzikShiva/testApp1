package app.absproj.api;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import absproj.email.api.EmailResponse;
import absproj.email.api.ErrorResponse;

public class NegativeEmailTests extends EmailBaseTest {
	private static final Logger logger = LogManager.getLogger(NegativeEmailTests.class);
	private static String inValid_api_key = "1a6504f94350464e15dcdc2b977!om";
	private static String valid_email = "itzikv3@gmail.com";
	private static String inValid_email = "itzikv3@gmail.c!om";
	private static String empty_email = "";

	@Test
	public static void invalidApiKeyTest() throws ClientProtocolException, IOException {
		CloseableHttpResponse httpResponse = emailService.getEmailValidation(inValid_api_key, valid_email);
		assertEquals(httpResponse.getStatusLine().getStatusCode(), 401);
		logger.info("got 401 response from server");

		ErrorResponse errorResponse = httpResponseToObj(httpResponse, ErrorResponse.class);
		assertEquals(errorResponse.getError().getMessage(), "Invalid API key provided.");
		logger.info("Invalid API key provided");
		assertEquals(errorResponse.getError().getCode(), "unauthorized");
		logger.info("unauthorized request");
		assertEquals(errorResponse.getError().getDetails(), null);
		logger.info("no details..");
	}

	
	//MOVE TO INVALID
	@Test
	public static void invalidEmailTest() throws ClientProtocolException, IOException {
		CloseableHttpResponse httpResponse = emailService.getEmailValidation(inValid_email);
		assertEquals(httpResponse.getStatusLine().getStatusCode(), 200);
		logger.info("got 200 response from server");

		EmailResponse emailResponse = httpResponseToObj(httpResponse, EmailResponse.class);
		// TODO - show Hod the result and if it necessary
//		assertEquals(emailResponse.getEmail() , valid_email);
//		logger.info("got valid email");
		assertTrue(!emailResponse.getAutocorrect().isBlank());
		logger.info("got valid autocorrect \"\" ");
		
		assertEquals(emailResponse.getDeliverability(), "UNDELIVERABLE");
		logger.info("got \"UNDELIVERABLE\" email");
		
		assertEquals(emailResponse.getQuality_score(), "0.00");
		logger.info("got email quality_score: 0.00");
		
		assertEquals(emailResponse.getIs_valid_format().getValue(), false);
		logger.info("got false email format");
		
		assertEquals(emailResponse.getIs_free_email().getValue(), false);
		logger.info("got false \"is_free_email: \"true response");
		
		assertEquals(emailResponse.getIs_disposable_email().getValue(), false);
		logger.info("got \"is_disposable_email\": false ");
		
		assertEquals(emailResponse.getIs_role_email().getValue(), false);
		logger.info("got \"is_role_email\": false ");
		
		assertEquals(emailResponse.getIs_catchall_email().getValue(), false);
		logger.info("got \"is_catchall_email\": false ");
		
		assertEquals(emailResponse.getIs_mx_found().getValue(), false);
		logger.info("got \"Is_mx_found\": false ");
		
		assertEquals(emailResponse.getIs_smtp_valid().getValue(), false);
		logger.info("got \"Is_smtp_valid\": false ");
	}

	@Test
	public static void emptyEmailTest() throws ClientProtocolException, IOException {
		CloseableHttpResponse httpResponse = emailService.getEmailValidation(empty_email);
		assertEquals(httpResponse.getStatusLine().getStatusCode(), 400);
		logger.info("got 400 response from server");

		//ASK - hod, this looks like assertion that including other or should i check all the fields?
		ErrorResponse errorResponse = httpResponseToObj(httpResponse, ErrorResponse.class);
		assertEquals(errorResponse.getError().getDetails().getEmail().get(0), "This is a required argument.");
		logger.info("got error massage ");
	}
}
