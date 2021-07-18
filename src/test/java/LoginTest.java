import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase{

    @BeforeMethod
    public void precondition(){
        if(!app.loginHelper().isLogged()){
            app.loginHelper().logout();
        }
    }
    @Test
    public void loginTestPositive(){

        app.loginHelper().openLoginForm();
        app.loginHelper().fillLoginForm("anat@gmail.com","Aa12345$");
        app.loginHelper().clickOkButtonLogIn();
        app.loginHelper().pause(3000);
        String contacts = app.loginHelper().getText(By.xpath("//a[.='CONTACTS']"));
        Assert.assertEquals(contacts, "CONTACTS");

    }
    @Test
    public void loginTestPositiveDto(){
        User user=new User().withEmail("anat@gmail.com").withPassword("Aa12345$");

        app.loginHelper().openLoginForm();
        app.loginHelper().fillLoginForm(user);
        app.loginHelper().clickOkButtonLogIn();
        app.loginHelper().pause(3000);
        String contacts = app.loginHelper().getText(By.xpath("//a[.='CONTACTS']"));
        Assert.assertEquals(contacts, "CONTACTS");

    }
}
