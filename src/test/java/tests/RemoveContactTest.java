package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTest extends TestBase{
    @BeforeMethod(alwaysRun = true)
    public void precondition(){
        if (!app.userHelper().isLogged()){
            app.userHelper().login(new User().withEmail("anat@gmail.com").withPassword("Aa12345$"));
        }
        app.ContactHelper().addOneContact();
    }

    @Test
    public void removeOneContact(){
        app.ContactHelper().removeOneContact();
    }

    @Test
    public void removeAllContacts(){
        Assert.assertTrue(app.ContactHelper().removeAllContacts());
    }
}
