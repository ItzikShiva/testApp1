package app.petstore.user.ui.android;

import io.appium.java_client.android.AndroidDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import app.petstore.user.ui.DashboardPage;
import app.petstore.user.ui.UIUtils;

import java.io.IOException;
import java.net.URL;

public class LoginTests extends UIUtils {
	private static final Logger Logger = LogManager.getLogger(LoginTests.class);

	private static AndroidDriver driver;
	private static LoginPageAndroid loginPage;

	@BeforeTest
	public void setUp() throws IOException, InterruptedException {
		// Set up desired capabilities
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "10.0");
		capabilities.setCapability("browserName", "chrome");

		driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
		Logger.info("new AndroidDriver was set-up with capabilities");

		// TODO - ask hod if this next lines are correct location?
		driver.get(basgeURI);
		loginPage = new LoginPageAndroid(driver);
		Logger.info("got into login page");
		takeScreenshot("login-page", driver);
	}

	@Test
	public static void loginValidTest() throws InterruptedException, IOException {
		loginPage.setUsername("test111");
		loginPage.setPassword("1234");
		Logger.info("values for login wat set");
		takeScreenshot("login-page after set inputs", driver);

		DashboardPage dashboardPage = loginPage.submit();
		Assert.assertTrue(dashboardPage.getSignoutButton().size() == 1);
		Logger.info("login success");
		takeScreenshot("login succesfully", driver);
	}

	@Test
	public static void loginNotValidTest() throws InterruptedException, IOException {
		loginPage.setUsername("test11111");
		loginPage.setPassword("1234");
		Logger.info("values for NOT Valid login wat set");
		takeScreenshot("login-page after set inputs", driver);

		DashboardPage dashboardPage = loginPage.submit();
		Assert.assertTrue(dashboardPage.getSignoutButton().size() == 0);
		Logger.info("login NOT success");
		takeScreenshot("login failed", driver);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
