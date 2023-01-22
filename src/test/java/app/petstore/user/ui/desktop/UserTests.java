package app.petstore.user.ui.desktop;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import app.petstore.user.ui.desktop.DashboardPage;
import app.petstore.user.ui.desktop.LoginPage;

import org.apache.logging.log4j.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;

public class UserTests {

	private static final Logger logger = LogManager.getLogger(UserTests.class);

//	static WebDriver driver = headlessDriver();
	static WebDriver driver = new ChromeDriver();
	static LoginPage loginPage = new LoginPage(driver);
	static String basgeURI = "https://petstore.octoperf.com/actions/Account.action?signonForm=";


	@Test
	public static void loginValidTest() throws InterruptedException, IOException {
		driver.get(basgeURI);
		takeScreenshot("1");
		loginPage.setUsername("test111");
		loginPage.setPassword("1234");
		takeScreenshot("2");
		
		DashboardPage dashboardPage = loginPage.submit();
		takeScreenshot("3");

		Assert.assertTrue(dashboardPage.getSignoutButton().size() == 1);
		Thread.sleep(1000);
		driver.close();

		// logging
		logger.debug("Some debug log");
//        logger.info("dashboardPage isLoggedin: " + dashboardPage.isLoggedin());
//        logger.info("Car2: " + car2);
		logger.warn("Warning accrued at " + LocalDateTime.now());
		logger.error("Error accrued at " + LocalDateTime.now());
		logger.fatal("Serious problem with car Login in accrued at " + LocalDateTime.now());
	}

	@Test
	public static void loginNotValidTest() throws InterruptedException {
		driver.get(basgeURI);

		loginPage.setUsername("test111");
		loginPage.setPassword("1234");

		DashboardPage dashboardPage = loginPage.submit();

		Assert.assertTrue(dashboardPage.getSignoutButton().size() == 0);

		Thread.sleep(1000);
		driver.close();
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

	
	public static WebDriver headlessDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		return new ChromeDriver(options);
	}
}
