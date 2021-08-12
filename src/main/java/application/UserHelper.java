package application;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserHelper extends HelperBase {
    public UserHelper(WebDriver wd) {
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
        return wd.findElements(By.xpath("//*[.='Sign Out']")).size() > 0;
    }

    public void logout() {
        click(By.xpath("//*[.='Sign Out']"));
    }

    public void waitForVisiblity(By locator) {
        new WebDriverWait(wd, 10).until(ExpectedConditions.visibilityOf(wd.findElement(locator)));
    }

    public void login(User user) {
        openLoginForm();
        fillLoginForm(user);
        clickOkButtonLogIn();
        new WebDriverWait(wd, 10).until(ExpectedConditions.visibilityOf(wd.findElement(By.xpath("//*[.='ADD']"))));

    }
}
