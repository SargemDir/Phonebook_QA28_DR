import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class TestBase {
    WebDriver wd;
    @BeforeClass
    public void init(){
        wd = new ChromeDriver();
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.navigate().to("https://contacts-app.tobbymarshall815.vercel.app/home");
    }
    @AfterClass
    public void tearDown(){
        wd.quit();
    }
    public void click(By locator){
        wd.findElement(locator).click();
    }
    public void type(By locator, String text){
        if(text != null) {
            WebElement element = wd.findElement(locator);
            element.click();
            element.clear();
            element.sendKeys(text);
        }
    }
    public String getText(By locator){
        return wd.findElement(locator).getText();
    }
    public void pause(int ts){
        try {
            Thread.sleep(ts);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
