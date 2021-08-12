package application;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class AppManager {
    //WebDriver wd;
    EventFiringWebDriver wd;
    UserHelper userHelper;
    ContactHelper contactHelper;
    String browser;

    public AppManager(String browser) {
        this.browser = browser;
    }
    Logger logger = LoggerFactory.getLogger(AppManager.class);

    public void start(){
        if(browser.equals(BrowserType.CHROME)){
            wd = new EventFiringWebDriver(new ChromeDriver());
            logger.info("Start in browser CHROME");
        }else if (browser.equals(BrowserType.FIREFOX)){
            wd= new EventFiringWebDriver(new FirefoxDriver());
            logger.info("Start in browser FIREFOX");
        }
        wd.register(new MyListener());
        //wd = new ChromeDriver();
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.navigate().to("https://contacts-app.tobbymarshall815.vercel.app/home");
        userHelper =new UserHelper(wd);
        contactHelper = new ContactHelper(wd);




    }
    public void stop(){
        wd.quit();
    }


    public UserHelper userHelper() {
        return userHelper;
    }

    public ContactHelper ContactHelper() {
        return contactHelper;
    }
}
