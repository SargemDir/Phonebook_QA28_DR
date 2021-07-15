import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    @Test
    public void loginTestPositive(){
        click(By.xpath("//a[.='LOGIN']"));
        type(By.xpath("//input[@placeholder='Email']"), "anat@gmail.com");
        type(By.xpath("//input[@placeholder='Password']"), "Aa12345$");
        click(By.xpath("//button[.=' Login']"));
        pause(3000);
        String contacts = getText(By.xpath("//a[.='CONTACTS']"));
        Assert.assertEquals(contacts, "CONTACTS");
    }
}
