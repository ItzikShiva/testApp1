package app.petstore.user.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class UserServiceUI {
	private static final Logger logger = LogManager.getLogger(UserServiceUI.class);

	public static void fillRegisterForm(WebDriver driver, String username, String password) {
		driver.findElement(By.xpath("//*[text()='Register Now!']")).click();
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("repeatedPassword")).sendKeys(password);
		driver.findElement(By.name("account.firstName")).sendKeys("itzik");
		driver.findElement(By.name("account.lastName")).sendKeys("test lastname");
		driver.findElement(By.name("account.email")).sendKeys("itzik@test.co.il");
		driver.findElement(By.name("account.phone")).sendKeys("050-999845");
		driver.findElement(By.name("account.address1")).sendKeys("address1");
		driver.findElement(By.name("account.address1")).sendKeys("address2");
		driver.findElement(By.name("account.city")).sendKeys("new city");
		driver.findElement(By.name("account.state")).sendKeys("state test");
		driver.findElement(By.name("account.zip")).sendKeys("zip test");
		driver.findElement(By.name("account.country")).sendKeys("country test");
		driver.findElement(By.name("newAccount")).click();
	}

	public static void createNewUserForTests(String username, String password) {
		WebDriver driver = headlessDriver();
//		WebDriver driver = new ChromeDriver();
		driver.get("https://petstore.octoperf.com/actions/Account.action?signonForm=");
		fillRegisterForm(driver, username, password);

		logger.info("user with username: " + username + " and password: " + password + " was created");
		driver.close();
	}

	public static WebDriver headlessDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		return new ChromeDriver(options);
	}
}
