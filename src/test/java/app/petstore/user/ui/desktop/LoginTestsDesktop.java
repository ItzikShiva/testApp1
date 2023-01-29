package app.petstore.user.ui.desktop;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import app.petstore.user.ui.DashboardPage;
import app.petstore.user.ui.UIUtils;
import app.petstore.user.ui.UserServiceUI;

import org.apache.logging.log4j.Logger;
import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;

public class LoginTestsDesktop {
	private static final Logger logger = LogManager.getLogger(LoginTestsDesktop.class);

	private static WebDriver driver;
	private static LoginPageDesktop loginPage;

	// TODO - ask, i want to take this vars up, should be extends "BaseTestUI"?
	private static String username = "test11";
	private static String password = "1234";

	@BeforeSuite
	public void testsSettings() throws ClientProtocolException, IOException {
		UserServiceUI.createNewUserForTests(username, password);
	}

	@BeforeMethod
	public void setUp() throws InterruptedException, IOException {
//	static WebDriver driver = headlessDriver();
		driver = new ChromeDriver();
		loginPage = new LoginPageDesktop(driver);
		driver.get(UIUtils.basgeURI);
		logger.info("got into login page");
		UIUtils.takeScreenshot("login-page", driver);
	}

	@Test
	public static void validInputs() throws InterruptedException, IOException {
		DashboardPage dashboardPage = loginPage.login(username, password);
		Assert.assertTrue(dashboardPage.isSignoutButtonExist());
		UIUtils.takeScreenshot("login succesfully", driver);
	}

	@Test
	public static void inValidUsername() throws InterruptedException, IOException {
		logger.info("start test - login with Invalid username - should fail");
		DashboardPage dashboardPage = loginPage.login("test11111", "1234");
		Assert.assertFalse(dashboardPage.isSignoutButtonExist());
		UIUtils.takeScreenshot("login failed as expectd", driver);
		logger.info("login failed as expected with invalid username");
	}

	@Test
	public static void emptyUsernameAndPassword() throws InterruptedException, IOException {
		logger.info("start test - empty inputs login should fail");
		DashboardPage dashboardPage = loginPage.login("", "");
		Assert.assertFalse(dashboardPage.isSignoutButtonExist());
		UIUtils.takeScreenshot("login failed as expectd", driver);
		logger.info("login failed as expected with empty username and password");
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

	public static WebDriver headlessDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		return new ChromeDriver(options);
	}
}
