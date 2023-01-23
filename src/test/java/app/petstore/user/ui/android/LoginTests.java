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
//		capabilities.setCapability("deviceName", "2772d5000a047ece");
//		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		capabilities.setCapability("platformVersion", "10.0");
		capabilities.setCapability("browserName", "chrome");

//More capabilities
//        capabilities.setCapability("appPackage", "app.petstore.user.ui");
//        capabilities.setCapability("appActivity", "app.petstore.user.ui.android");
//        capabilities.setCapability("app", "C:\\Apps\\android-sdk\\Sample_Android_App-Test_1.0_Apkpure.apk");
//        capabilities.setCapability("automationName", "UiAutomator2");

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
		takeScreenshot("login-page after set inputs", driver);

		DashboardPage dashboardPage = loginPage.submit();
		Assert.assertTrue(dashboardPage.getSignoutButton().size() == 1);
		takeScreenshot("login succesfully", driver);
	}

	@Test
	public static void loginNotValidTest() throws InterruptedException, IOException {
		loginPage.setUsername("test11111");
		loginPage.setPassword("1234");
		takeScreenshot("login-page after set inputs", driver);

		DashboardPage dashboardPage = loginPage.submit();
		Assert.assertTrue(dashboardPage.getSignoutButton().size() == 0);
		takeScreenshot("login failed", driver);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
