package application;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;
    LoginHelper loginHelper;
    String browser;
    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init(){
        if(browser.equals(BrowserType.CHROME)){
            wd = new ChromeDriver();
        }else if (browser.equals(BrowserType.FIREFOX)){
            wd= new FirefoxDriver();
        }
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.navigate().to("https://contacts-app.tobbymarshall815.vercel.app/home");
        loginHelper = new LoginHelper(wd);
    }

    public void stop(){
        wd.quit();
    }

    public LoginHelper loginHelper() {
        return loginHelper;
    }
}
