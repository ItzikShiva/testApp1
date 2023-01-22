package app.petstore.user.ui.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

import java.util.List;

public class DashboardPage {
    private AndroidDriver driver;

//TODO - ask if ok, because "driver" is also null before constructor. so in the constructor i did the assignment.
    private List<WebElement> signoutButton=null;
//    private List<WebElement> signinButton=null;

    public List<WebElement> getSignoutButton() {
        return signoutButton;
    }

    @FindBy(linkText = "Sign In")
    private WebElement signinButton;

    public DashboardPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
//        signoutButton = this.driver.findElements(By.cssSelector("a[href='/actions/Account.action?signoff=']"));
    }


}
