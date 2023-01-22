package app.petstore.user.ui.desktop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class LoginPage {
	private WebDriver driver;

	@FindBy(name = "username")
	private WebElement usernameField;

	@FindBy(name = "password")
	private WebElement passwordField;

	@FindBy(name = "signon")
	private WebElement submitButton;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void setUsername(String username) {
		usernameField.sendKeys(username);
	}

	public void setPassword(String password) {
		passwordField.clear();
		passwordField.sendKeys(password);
	}

	public DashboardPage submit() {
		submitButton.click();
		return new DashboardPage(this.driver);
	}

}
