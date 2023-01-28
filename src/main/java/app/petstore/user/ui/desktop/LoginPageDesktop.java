package app.petstore.user.ui.desktop;

import org.openqa.selenium.WebDriver;

import app.petstore.user.ui.DashboardPage;
import app.petstore.user.ui.LoginPage;

public class LoginPageDesktop extends LoginPage {

	public LoginPageDesktop(WebDriver driver) {
		super(driver);
	}

	@Override
	public DashboardPage submit() {
		submitButton.click();
		return new DashboardPage(driver);
	}

}
