package UI.petStore.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashboardPage {
    private WebDriver driver;

//TODO - ask if ok, because "driver" is also null before constructor. so in the constructor i did the assignment.
    private List<WebElement> signoutButton=null;
//    private List<WebElement> signinButton=null;

    public List<WebElement> getSignoutButton() {
        return signoutButton;
    }

    @FindBy(linkText = "Sign In")
    private WebElement signinButton;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        signoutButton = this.driver.findElements(By.cssSelector("a[href='/actions/Account.action?signoff=']"));
    }


}
