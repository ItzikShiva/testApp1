package app.petstore.user.ui;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class DashboardPage {
	@FindBy(css = "a[href='/actions/Account.action?signoff=']")
	private List<WebElement> signoutButton;

	public List<WebElement> getSignoutButton() {
		return signoutButton;
	}

	@FindBy(linkText = "Sign In")
	private WebElement signinButton;

	public DashboardPage(AndroidDriver driver) {
		super();
		PageFactory.initElements(driver, this);
	}

	public DashboardPage(WebDriver driver) {
		super();
		PageFactory.initElements(driver, this);
	}

}
