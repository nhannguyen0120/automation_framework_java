package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserControl {
    public static WebDriver browser;

    public WebDriver newBrowser() {
        return browser = setLocalBrowser(TestConfig.getBrowser());
    }

    private WebDriver setLocalBrowser(TestConfig.Browser browser) {
        setDriversPath(browser);
        return switch (browser) {
            case FIREFOX -> new FirefoxDriver();
            case IE -> new InternetExplorerDriver();
            case SAFARI -> new SafariDriver();
            case EDGE -> new EdgeDriver();
            default -> new ChromeDriver();
        };
    }

    private void setDriversPath(TestConfig.Browser browser) {
        switch (browser) {
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
            case EDGE:
                WebDriverManager.edgedriver().setup();
            case IE:
                WebDriverManager.iedriver().setup();
            default:
                WebDriverManager.chromedriver().setup();
        }
    }

    public static WebDriver reloadBrowser() {
        browser.navigate().refresh();
        return browser;
    }
}
