package common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.awt.*;

public class Fixtures {

    public static class SetUp {

        public static WebDriver initBrowser(String applicationURL) {
            BrowserControl bc = new BrowserControl();
            WebDriver browser = bc.newBrowser();
            maximizeWindow(browser);
            browser.get(applicationURL);
            return browser;
        }
    }

    public static class TearDown {
        public static void close(WebDriver browser) {
            if (browser != null) {
                browser.quit();
            }
        }
    }

    private static void maximizeWindow(WebDriver browser) {
        browser.manage().window().maximize();
    }
}
