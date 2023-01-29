package app.petstore.user.ui.android;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import app.petstore.user.ui.DashboardPage;
import app.petstore.user.ui.LoginPage;

public class LoginPageAndroid extends LoginPage {

	public LoginPageAndroid(WebDriver driver) {
		super(driver);
	}

	@Override
	public DashboardPage submit() {
		submitButton.sendKeys(Keys.ENTER);
		return new DashboardPage(driver);
	}

}
