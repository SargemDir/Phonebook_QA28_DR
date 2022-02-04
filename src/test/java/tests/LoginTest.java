package tests;

import application.MyDataProvider;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginTest extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void precondition() {
        if (app.userHelper().isLogged()) {
            app.userHelper().logout();
        }
    }

    @Test
    public void loginTestBase() {
        User user = new User()
                .withEmail("anat@gmail.com")
                .withPassword("Aa12345$");

        app.userHelper().openLoginForm();
        app.userHelper().fillLoginForm(user);
        logger.info("Login with --> email: \"" + user.getEmail() + "\", password: \"" + user.getPassword() + "\"");
        app.userHelper().clickOkButtonLogIn();
        app.userHelper().waitForVisiblity(By.xpath("//*[.='Sign Out']"));
        Assert.assertTrue(app.userHelper().isLogged());
    }

    @Test(dataProvider = "validLoginDataClassDP", dataProviderClass = MyDataProvider.class)
    public void loginTestDataProviderClass(String email, String password) {
        User user = new User()
                .withEmail(email)
                .withPassword(password);

        app.userHelper().openLoginForm();
        app.userHelper().fillLoginForm(user);
//        logger.info("Login with --> email: \"" + user.getEmail() + "\", password: \"" + user.getPassword() + "\"");
        app.userHelper().clickOkButtonLogIn();
        app.userHelper().waitForVisiblity(By.xpath("//a[.='CONTACTS']"));
        Assert.assertTrue(app.userHelper().isLogged());
    }

    @Test(dataProvider = "dataFileCSV", dataProviderClass = MyDataProvider.class)
    public void loginTestDP_CSV(User user) {
        app.userHelper().openLoginForm();
        app.userHelper().fillLoginForm(user);
        logger.info("Login with --> email: \"" + user.getEmail() + "\", password: \"" + user.getPassword() + "\"");
        app.userHelper().clickOkButtonLogIn();
        app.userHelper().waitForVisiblity(By.xpath("//*[.='Sign Out']"));
        Assert.assertTrue(app.userHelper().isLogged());
    }

    @Test
    public void loginTestWithInvalidPassword() {
        User user = new User()
                .withEmail("anat@gmail.com")
                .withPassword("Aa12345");

        app.userHelper().openLoginForm();
        app.userHelper().fillLoginForm(user);
        logger.info("Login with --> email: \"" + user.getEmail() + "\", password: \"" + user.getPassword() + "\"");
        app.userHelper().clickOkButtonLogIn();
        app.userHelper().acceptAlert();
        Assert.assertFalse(app.userHelper().isLogged());
    }
}
