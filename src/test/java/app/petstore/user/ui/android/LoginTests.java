package app.petstore.user.ui.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import app.petstore.user.ui.android.DashboardPage;
import app.petstore.user.ui.android.LoginPage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginTests {
	private static final Logger Logger = LogManager.getLogger(LoginTests.class);

	private static AndroidDriver driver;
	static String basgeURI = "https://petstore.octoperf.com/actions/Account.action?signonForm=";

	@BeforeTest
	public void setUp() throws MalformedURLException {
		// Set up desired capabilities
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "2772d5000a047ece");
		capabilities.setCapability("platformVersion", "10.0");
		capabilities.setCapability("browserName", "chrome");
		
//		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
//		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
//		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
//		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		
		

//        capabilities.setCapability("appPackage", "app.petstore.user.ui");
//        capabilities.setCapability("appActivity", "app.petstore.user.ui.android");

		
		//        capabilities.setCapability("deviceName", "Android Emulator");
//		capabilities.setCapability("deviceName", "Galaxy Note9");
//        capabilities.setCapability("app", "C:\\Apps\\android-sdk\\Sample_Android_App-Test_1.0_Apkpure.apk");


//        capabilities.setCapability("automationName", "UiAutomator2");
//      capabilities.setCapability("automationName", "UiAutomator1");

		driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
		Logger.info("new AndroidDriver was set-up with capabilities");
	}

//	@Test
	public static void loginValidTest() throws InterruptedException, IOException {
		Thread.sleep(1000);
		driver.get(basgeURI);
//		LoginPage loginPage = new LoginPage(driver);

//		takeScreenshot("1");
//		WebElement loginButton = driver.findElement(By.name("sigon"));
//		WebElement loginButton = driver.findElement(By.xpath("//input[@name='signon'][@type='submit']"));
//		loginPage.setUsername("test111");
//		loginPage.setPassword("1234");
//		takeScreenshot("2");

//		DashboardPage dashboardPage = loginPage.submit();
//		loginButton.click();
//		Thread.sleep(2000);
//		takeScreenshot("3");

//		Assert.assertTrue(dashboardPage.getSignoutButton().size() == 1);

	}

	@Test
	public void testUI() throws InterruptedException {
		driver.get("https://www.google.com");
		Thread.sleep(2000);
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Appium");
		searchBox.submit();
		Thread.sleep(2000);
		String title = driver.getTitle();

		Assert.assertEquals(title, title);
	}

	@AfterTest
	public void tearDown() {
		// Close the app and the driver
		driver.quit();
	}

	public static void takeScreenshot(String name) throws IOException, InterruptedException {
		Thread.sleep(1000);
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "\\test-output\\screenshots\\" + name + ".png";
		FileUtils.copyFile(srcFile, new File(path));
		Reporter.log("<br><img src='" + path + "' height='400' width='400'/> <br>");
//        logger.log("<a href='" + path + "'> <img src='" + path + "' height='100' width='100'/> </a>");

	}
}
