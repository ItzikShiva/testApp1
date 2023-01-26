package app.petstore.user.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
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
			return signoutButton.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

}
