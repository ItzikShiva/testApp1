package app.petstore.user.ui.android;

import java.io.IOException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import app.petstore.user.ui.UIUtils;
import io.appium.java_client.android.AndroidDriver;

public class CalcAppTests extends UIUtils {
	private static final Logger LOGGER = LogManager.getLogger(CalcAppTests.class);

	private static AndroidDriver driver;
	private static WebElement seven;
	private static WebElement plus;
	private static WebElement six;
	private static WebElement equal;
	private static WebElement minus;
	private static WebElement five;
	private static WebElement divide;
	private static WebElement one;
	private static WebElement multiply;
	private static WebElement four;

	@BeforeTest
	public void setUp() throws IOException, InterruptedException {
		// TODO - hod, is it ok the assert here?(it's not a "test")
		Assert.assertTrue(checkFileExist(System.getProperty("user.dir") + "\\apk\\SimpleCalculator.apk"));
		LOGGER.info("APK file was found");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		setCalcCapabilities(capabilities);

		driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
		LOGGER.info("new AndroidDriver was set-up with capabilities");

		seven = getElementByID("button_seven");
		plus = getElementByID("button_plus");
		six = getElementByID("button_six");
		equal = getElementByID("button_equals");
		minus = getElementByID("button_minus");
		five = getElementByID("button_five");
		divide = getElementByID("button_divide");
		one = getElementByID("button_one");
		multiply = getElementByID("button_multiply");
		four = getElementByID("button_four");

		LOGGER.info("calc elements were set");
	}

	@Test
	public static void arithmeticPlusTest() throws InterruptedException, IOException {
		LOGGER.info("starting plus operation");
		seven.click();
		plus.click();
		six.click();
		equal.click();
		takeScreenshot("plus_test", driver);
		Assert.assertEquals(getResult(), "13");
		LOGGER.info("plus operation finished");
		clearResult();
	}

	@Test
	public static void arithmeticMinusTest() throws InterruptedException, IOException {
		LOGGER.info("starting minus operation");
		seven.click();
		seven.click();
		minus.click();
		seven.click();
		equal.click();
		takeScreenshot("minus_test", driver);
		Assert.assertEquals(getResult(), "70");
		LOGGER.info("minus operation finished");
		clearResult();
	}

	@Test
	public static void arithmeticMultiplyTest() throws InterruptedException, IOException {
		LOGGER.info("starting multiply operation");
		five.click();
		multiply.click();
		four.click();
		equal.click();
		takeScreenshot("multiply_test", driver);
		Assert.assertEquals(getResult(), "20");
		LOGGER.info("multiply operation finished");
		clearResult();
	}

	@Test
	public static void arithmeticDivisionTest() throws InterruptedException, IOException {
		LOGGER.info("starting divide operation");
		five.click();
		five.click();
		divide.click();
		one.click();
		one.click();
		equal.click();
		takeScreenshot("divide_test", driver);
		Assert.assertEquals(getResult(), "5");
		LOGGER.info("divide operation finished");
		clearResult();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	private static void setCalcCapabilities(DesiredCapabilities capabilities) {
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("platformVersion", "10.0");
		capabilities.setCapability("app", System.getProperty("user.dir") + "\\apk\\SimpleCalculator.apk");
		capabilities.setCapability("automationName", "UiAutomator2");
	}

	// in getElementByID() "driver" comes from class variables
	public static WebElement getElementByID(String ID) {
		return driver.findElement(By.id(ID));
	}

	public static String getResult() {
		WebElement result = getElementByID("editText_result");
		return result.getText().substring(1);
	}

	// TODO - Hod, clear result not have to be but i think it's better way -
	// opinion..
	public static void clearResult() {
		WebElement cancelButton = getElementByID("button_cancel");
		cancelButton.click();
		LOGGER.info("calc results cleared");
	}

}
