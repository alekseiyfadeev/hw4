import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.nio.channels.FileLockInterruptionException;

enum WebDriverName {
    CHROME, FIREFOX
}

public class WebDriverFactory {

    private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);

    public static WebDriver createNewDriver(String webDriverName) {

        return createNewDriver(webDriverName, null);
    }

    public static WebDriver createNewDriver(String webDriverName,
                                            MutableCapabilities options) {

        WebDriverName value = WebDriverName.CHROME;
        try {
            value = WebDriverName.valueOf(webDriverName.toUpperCase());
            logger.info("Specified web driver name: '" + webDriverName + "' will be used to start web driver");
        }
        catch (IllegalArgumentException e) {
            if (!webDriverName.equals("")) {
                logger.warn("Specified web driver name: '" + webDriverName + "' is not a supported web driver name. Default value: '" + value.toString() + "' will be used to start web driver");
            }
            else {
                logger.warn("Web driver name is not specified. Default value: '" + value.toString() + "' will be used to start web driver");
            }
        }

        WebDriver driver = null;
        switch (value) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                if (options!=null) {
                    driver = new ChromeDriver(options);
                }
                else {
                    driver = new ChromeDriver();
                }
                break;

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                if (options!=null) {
                    driver = new FirefoxDriver(options);
                }
                else {
                    driver = new FirefoxDriver();
                }
                break;
        }
        logger.info(driver.toString() + " started");

        return  driver;
    }
}