package app.petstore.user.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class LoginPage {
	public WebDriver driver;

	@FindBy(name = "username")
	public WebElement usernameField;

	@FindBy(name = "password")
	public WebElement passwordField;

	@FindBy(name = "signon")
	public WebElement submitButton;

	public LoginPage(WebDriver driver) {
		super();
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public abstract DashboardPage submit(String username, String password);
}
