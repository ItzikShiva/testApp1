package app.absproj.api;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

//import BaseTest;
import absproj.email.api.EmailService;
import app.BaseTest;

public class EmailBaseTest extends BaseTest {

	static EmailService emailService = new EmailService();

//	@BeforeTest // annotated method placed in the beginning.
	@BeforeMethod
	public void before_test() throws InterruptedException {
		Thread.sleep(1000);
	}

}
