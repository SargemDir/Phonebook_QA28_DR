package tests;

import application.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class TestBase {
    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeMethod
    public void startLogger(Method m) {
        logger.info("Start method  --> " + m.getName());
    }

    @AfterMethod
    public void endofLogger(Method m) {
        logger.info("End of method --> " + m.getName());
    }

    @BeforeClass
    public void start() {
        app.init();
    }

    @AfterClass
    public void tearDown() {
        app.stop();
    }
}
