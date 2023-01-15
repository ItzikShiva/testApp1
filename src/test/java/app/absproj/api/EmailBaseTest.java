package app.absproj.api;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

//import BaseTest;
import absproj.email.api.EmailService;
import app.BaseTest;

public class EmailBaseTest extends BaseTest {

	static EmailService emailService = new EmailService();

	//This sleep is for prevent 429 error(too many requests)
	@BeforeMethod
	public void before_test_method() throws InterruptedException {
		Thread.sleep(1000);
	}

}
