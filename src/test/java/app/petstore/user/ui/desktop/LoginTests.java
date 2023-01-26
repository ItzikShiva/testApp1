package app.petstore.user.ui.desktop;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import app.petstore.user.ui.DashboardPage;
import app.petstore.user.ui.UIUtils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class LoginTests {
	private static final Logger logger = LogManager.getLogger(LoginTests.class);

	static WebDriver driver;
	static LoginPageDesktop loginPage;

	@BeforeMethod
	public void setUp() {
//	static WebDriver driver = headlessDriver();
		driver = new ChromeDriver();
		loginPage = new LoginPageDesktop(driver);
		driver.get(UIUtils.basgeURI);
	}

	@Test
	public static void loginValidTest() throws InterruptedException, IOException {
		UIUtils.takeScreenshot("login-page before inputs", driver);
		logger.info("login-page before inputs");
		DashboardPage dashboardPage = loginPage.submit("test111", "1234");
		UIUtils.takeScreenshot("3", driver);

		Assert.assertTrue(dashboardPage.isSignoutButtonExist());
		UIUtils.takeScreenshot("login succesfully", driver);
		logger.info("login succesfully");
	}

	@Test
	public static void loginNotValidTest() throws InterruptedException, IOException {
		UIUtils.takeScreenshot("login-page before not valid inputs", driver);
		logger.info("login-page before not valid inputs");
		DashboardPage dashboardPage = loginPage.submit("test11111", "1234");
		Assert.assertFalse(dashboardPage.isSignoutButtonExist());
		UIUtils.takeScreenshot("login failed", driver);
		logger.info("login failed");
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
