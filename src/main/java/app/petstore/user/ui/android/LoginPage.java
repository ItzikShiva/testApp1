package app.petstore.user.ui.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class LoginPage {
	private AndroidDriver driver;

	@FindBy(name = "username")
	private WebElement usernameField;

	@FindBy(name = "password")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@name='signon'][@type='submit']")
	private WebElement submitButton;

	public LoginPage(AndroidDriver driver) {
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
