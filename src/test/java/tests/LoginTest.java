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

    @BeforeMethod
    public void precondition() {
        if (app.loginHelper().isLogged()) {
            app.loginHelper().logout();
        }
    }

    @Test
    public void loginTestBase() {
        User user = new User()
                .withEmail("anat@gmail.com")
                .withPassword("Aa12345$");

        app.loginHelper().openLoginForm();
        app.loginHelper().fillLoginForm(user);
        logger.info("Login with --> email: \"" + user.getEmail() + "\", password: \"" + user.getPassword() + "\"");
        app.loginHelper().clickOkButtonLogIn();
        app.loginHelper().waitForVisiblity(By.xpath("//*[.='Sign Out']"));
        Assert.assertTrue(app.loginHelper().isLogged());
    }



    @Test(dataProvider = "validLoginDataClassDP", dataProviderClass = MyDataProvider.class)
    public void loginTestDataProviderClass(String email, String password) {
        User user = new User()
                .withEmail(email)
                .withPassword(password);

        app.loginHelper().openLoginForm();
        app.loginHelper().fillLoginForm(user);
//        logger.info("Login with --> email: \"" + user.getEmail() + "\", password: \"" + user.getPassword() + "\"");
        app.loginHelper().clickOkButtonLogIn();
        app.loginHelper().waitForVisiblity(By.xpath("//a[.='CONTACTS']"));
        Assert.assertTrue(app.loginHelper().isLogged());
    }

    @Test(dataProvider = "dataFileCSV", dataProviderClass = MyDataProvider.class)
    public void loginTestDP_CSV(User user) {
        app.loginHelper().openLoginForm();
        app.loginHelper().fillLoginForm(user);
        logger.info("Login with --> email: \"" + user.getEmail() + "\", password: \"" + user.getPassword() + "\"");
        app.loginHelper().clickOkButtonLogIn();
        app.loginHelper().waitForVisiblity(By.xpath("//*[.='Sign Out']"));
        Assert.assertTrue(app.loginHelper().isLogged());
    }
}
