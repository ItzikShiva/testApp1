package app.petstore.user.ui.android;

import org.openqa.selenium.Keys;

import app.petstore.user.ui.DashboardPage;
import app.petstore.user.ui.LoginPage;
import io.appium.java_client.android.AndroidDriver;

public class LoginPageAndroid extends LoginPage {
	private AndroidDriver driver;

	public LoginPageAndroid(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public DashboardPage submit() {
		submitButton.sendKeys(Keys.ENTER);
		return new DashboardPage(this.driver);
	}

}
