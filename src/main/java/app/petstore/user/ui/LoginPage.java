package app.petstore.user.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class LoginPage {
	@FindBy(name = "username")
	public WebElement usernameField;

	@FindBy(name = "password")
	public WebElement passwordField;

	@FindBy(name = "signon")
	public WebElement submitButton;

	public void setUsername(String username) {
		usernameField.sendKeys(username);
	}

	public void setPassword(String password) {
		passwordField.clear();
		passwordField.sendKeys(password);
	}

	public LoginPage(AndroidDriver driver) {
		super();
		PageFactory.initElements(driver, this);
	}

	public LoginPage(WebDriver driver) {
		super();
		PageFactory.initElements(driver, this);
	}

}
