package app.petstore.user.ui.android;

import io.appium.java_client.android.AndroidDriver;

import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import app.petstore.user.ui.DashboardPage;
import app.petstore.user.ui.UIUtils;
import app.petstore.user.ui.UserServiceUI;

import java.io.IOException;
import java.net.URL;

public class LoginTestsAndroid {
	private static final Logger logger = LogManager.getLogger(LoginTestsAndroid.class);

	private static WebDriver driver;
	private static LoginPageAndroid loginPage;

	@BeforeSuite
	public void testsSettings() throws ClientProtocolException, IOException {
		UserServiceUI.createNewUserForTests("test11", "1234");
	}

	@BeforeTest
	public void setUp() throws IOException, InterruptedException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		setCapabilities(capabilities);

		driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
		logger.info("new AndroidDriver was set-up with capabilities");

		driver.get(UIUtils.basgeURI);
		loginPage = new LoginPageAndroid(driver);
		logger.info("got into login page");
		UIUtils.takeScreenshot("login-page", driver);
	}

	@Test
	public static void validInputs() throws InterruptedException, IOException {
		DashboardPage dashboardPage = loginPage.login("test11", "1234");
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

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	private static void setCapabilities(DesiredCapabilities capabilities) {
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "10.0");
		capabilities.setCapability("browserName", "chrome");
	}
}
