package app.petstore.user.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class DashboardPage {
	private static final Logger logger = LogManager.getLogger(DashboardPage.class);

	@FindBy(css = "a[href='/actions/Account.action?signoff=']")
	private WebElement signoutButton;

	@FindBy(linkText = "Sign In")
	private WebElement signinButton;

	public DashboardPage(WebDriver driver) {
		super();
		PageFactory.initElements(driver, this);
	}

	public boolean isSignoutButtonExist() {
		try {
			boolean isDisplayed = signoutButton.isDisplayed();
			logger.info("signout button exist - login success");
			return isDisplayed;
		} catch (Exception e) {
			logger.info("signout button NOT exist - login failed");
			return false;
		}
	}

}
