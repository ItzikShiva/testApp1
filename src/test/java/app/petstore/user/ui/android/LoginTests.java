package app.petstore.user.ui.android;

import io.appium.java_client.android.AndroidDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import app.petstore.user.ui.DashboardPage;
import app.petstore.user.ui.UIUtils;

import java.io.IOException;
import java.net.URL;

public class LoginTests {
	private static final Logger logger = LogManager.getLogger(LoginTests.class);

	private static WebDriver driver;
	private static LoginPageAndroid loginPage;

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
	public static void loginValidTest() throws InterruptedException, IOException {
		UIUtils.takeScreenshot("login-page before valid inputs", driver);
		DashboardPage dashboardPage = loginPage.submit("test111", "1234");
		Assert.assertTrue(dashboardPage.isSignoutButtonExist());
		UIUtils.takeScreenshot("login succesfully", driver);
	}

	@Test
	public static void loginNotValidTest() throws InterruptedException, IOException {
		UIUtils.takeScreenshot("login-page before not valid inputs", driver);
		logger.info("login-page before not valid inputs");
		DashboardPage dashboardPage = loginPage.submit("test11111", "1234");
		Assert.assertTrue(dashboardPage.isSignoutButtonExist());
		UIUtils.takeScreenshot("login failed", driver);
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
