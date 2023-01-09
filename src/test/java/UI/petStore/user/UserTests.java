package UI.petStore.user;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import UI.petStore.user.UserService;

public class UserTests {
    //TODO - ask what is "final", it works also without
    private static final Logger logger = LogManager.getLogger(UserTests.class);

    static WebDriver driver = new ChromeDriver();
    static LoginPage loginPage = new LoginPage(driver);
    static String basgeURI = "https://petstore.octoperf.com/actions/Account.action?signonForm=";

    @Test
    public static void loginValidTest() throws InterruptedException {
        //TODO - ASK - GENERAL QUESTIONS - i made this class but not sure if need.. cause the methods are in the POM?
        UserService userService = new UserService();
        driver.get(basgeURI);

        loginPage.setUsername("test11");
        loginPage.setPassword("1234");

        //option 1
        DashboardPage dashboardPage = loginPage.submit();
        //TODO - ask - in DashboardPage i have constructor(it have to be right? because the Class use the "driver". i ask: i cant call in static way to isLoggedin() whithout constructor here)
        //and you told me maybe to return POM inside another POM (like loginPage).  i did something, option1

        //option 2
        //DashboardPage dashboardPage = new DashboardPage(driver);
        //TODO - ask - to check the opposite (failure) i need to create another Test or to assert here just to false? - another test
        Assert.assertTrue(dashboardPage.getSignoutButton().size() == 1);
        Thread.sleep(1000);
        driver.close();


        // logging
        logger.debug("Some debug log");
//        logger.info("dashboardPage isLoggedin: " + dashboardPage.isLoggedin());
//        logger.info("Car2: " + car2);
        logger.warn("Warning accrued at " + LocalDateTime.now());
        logger.error("Error accrued at " + LocalDateTime.now());
        logger.fatal("Serious problem with car Login in accrued at " + LocalDateTime.now());
    }


    @Test
    public static void loginNotValidTest() throws InterruptedException {
        driver.get(basgeURI);

        loginPage.setUsername("test111");
        loginPage.setPassword("1234");

        DashboardPage dashboardPage = loginPage.submit();
        //TODO - ask - the app is crash here if no dahboardPage, so i did something in isLoggedin(). tell me if it's good
        // Answer - we did findElemnts - toshow hod the null driver inside DashboardPage

        Assert.assertTrue(dashboardPage.getSignoutButton().size() == 0);

        Thread.sleep(1000);
        driver.close();
    }

}
