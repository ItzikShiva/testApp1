package app.petstore.user.ui;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import io.appium.java_client.android.AndroidDriver;

public class UIUtils {
	public static String basgeURI = "https://petstore.octoperf.com/actions/Account.action?signonForm=";

	public static void takeScreenshot(String name, AndroidDriver driver) throws IOException, InterruptedException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		getScreenshot(name, srcFile);
	}

	public static void takeScreenshot(String name, WebDriver driver) throws InterruptedException, IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		getScreenshot(name, srcFile);
	}

	private static void getScreenshot(String name, File srcFile) throws InterruptedException, IOException {
		Thread.sleep(1000);
		System.setProperty("org.uncommons.reportng.escape-output", "false");

		String path = System.getProperty("user.dir") + "\\test-output\\screenshots\\" + getFormattedDate() + "" + name
				+ ".png";
		FileUtils.copyFile(srcFile, new File(path));
		Reporter.log("<br><img src='" + path + "' height='400' width='400'/> <br>");
	}

	public static String getFormattedDate() {
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-mm-yyyy HH_mm_ss");
		return myDateObj.format(myFormatObj);
	}
}
