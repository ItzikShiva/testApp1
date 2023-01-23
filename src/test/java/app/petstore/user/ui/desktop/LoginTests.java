package app.petstore.user.ui.desktop;

import java.io.IOException;
import java.time.LocalDateTime;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import app.petstore.user.ui.DashboardPage;
import app.petstore.user.ui.UIUtils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class LoginTests extends UIUtils {

	private static final Logger logger = LogManager.getLogger(LoginTests.class);

	static WebDriver driver = headlessDriver();
//	static WebDriver driver = new ChromeDriver();
	static LoginPageDesktop loginPage = new LoginPageDesktop(driver);

	@Test
	public static void loginValidTest() throws InterruptedException, IOException {
		driver.get(basgeURI);
		takeScreenshot("1", driver);
		loginPage.setUsername("test111");
		loginPage.setPassword("1234");
		takeScreenshot("2", driver);

		DashboardPage dashboardPage = loginPage.submit();
		takeScreenshot("3", driver);

		Assert.assertTrue(dashboardPage.getSignoutButton().size() == 1);

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

		loginPage.setUsername("test11111");
		loginPage.setPassword("1234");

		DashboardPage dashboardPage = loginPage.submit();
		Assert.assertTrue(dashboardPage.getSignoutButton().size() == 0);
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}

	public static WebDriver headlessDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		return new ChromeDriver(options);
	}
}
