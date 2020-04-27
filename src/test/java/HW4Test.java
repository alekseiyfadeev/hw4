import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


public class HW4Test {

    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(HW4Test.class);
    @Before
    public void beforeTest() {
        String webDriverName = System.getProperty("browser");
        driver = WebDriverFactory.createNewDriver(webDriverName);
    }

    @After
    public void afterTest() {
        if (driver != null) {
            driver.quit();
            logger.info(driver.toString() + " quited");
        }
        else {
            logger.warn("Web driver to quit not found");
        }
    }

    @Test
    public void openOtusSite() {
        driver.get("https://otus.ru");
        logger.info("OTUS site is opened");

    }
}
