package tests;

import application.AppManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class TestBase {
    protected static AppManager app = new AppManager(System.getProperty("browser", BrowserType.CHROME));
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeMethod
    public void startLogger(Method m) {
        logger.info("Start method  --> " + m.getName());
    }

    @AfterMethod
    public void endofLogger(Method m) {
        logger.info("End of method --> " + m.getName());
    }

    @BeforeSuite(alwaysRun = true)
    public void start() {
        app.start();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }
}
