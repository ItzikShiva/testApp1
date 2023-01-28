package app.petstore.user.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class LoginPage {
	public WebDriver driver;
	private static final Logger logger = LogManager.getLogger(LoginPage.class);
	
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

	public void setUsername(String username) {
		usernameField.sendKeys(username);
	}

	public void setPassword(String password) {
		passwordField.clear();
		passwordField.sendKeys(password);
	}

	public abstract DashboardPage submit();

	public DashboardPage login(String username, String password) {
		logger.info("Login with user:" + username + " & password: " + password);
		setUsername(username);
		setPassword(password);
		return submit();
	}

}
