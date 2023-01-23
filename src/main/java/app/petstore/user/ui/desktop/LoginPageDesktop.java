package app.petstore.user.ui.desktop;

import org.openqa.selenium.WebDriver;

import app.petstore.user.ui.DashboardPage;
import app.petstore.user.ui.LoginPage;

public class LoginPageDesktop extends LoginPage {
	private WebDriver driver;

	public LoginPageDesktop(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public DashboardPage submit() {
		submitButton.click();
		return new DashboardPage(this.driver);
	}

}
