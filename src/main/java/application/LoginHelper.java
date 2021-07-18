package application;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginHelper extends HelperBase {
    public LoginHelper(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        click(By.xpath("//a[.='LOGIN']"));
    }

    public void fillLoginForm(String email, String password) {
        type(By.xpath("//input[@placeholder='Email']"), email);
        type(By.xpath("//input[@placeholder='Password']"), password);
    }

    public void fillLoginForm(User user) {
        type(By.xpath("//input[@placeholder='Email']"), user.getEmail());
        type(By.xpath("//input[@placeholder='Password']"), user.getPassword());
    }

    public void clickOkButtonLogIn() {
        click(By.xpath("//button[.=' Login']"));
    }

    public boolean isLogged() {
        return wd.findElements(By.xpath("//a[.='LOGIN']")).size() > 0;
    }

    public void logout() {
        click(By.xpath("//*[.='Sign Out']"));
    }
}
