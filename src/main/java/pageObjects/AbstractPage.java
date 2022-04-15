package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static common.BrowserControl.browser;

public class AbstractPage {
    By by;
    Select select;
    Actions action;
    WebElement element;
    long shortTimeout = 5;
    long longTimeout = 30;
    WebDriver driver = browser;
    List<WebElement> elements;
    JavascriptExecutor jsExecutor;
    WebDriverWait waitExplicit;
    public HashMap<String, HashMap<String, String>> dataAPIs;
    LocalTime getTime;
    LocalDate getDate;
    Locale locale;

    public AbstractPage() {
        jsExecutor = (JavascriptExecutor) driver;
        waitExplicit = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        action = new Actions(driver);
    }

    protected WebDriver getDriver() {
        return browser;
    }

    public void openUrl(String urlValue) {
        driver.get(urlValue);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getPageCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageCurrentSource() {
        return driver.getPageSource();
    }

    public void backToPage() {
        driver.navigate().back();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void forwardToPage() {
        driver.navigate().forward();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void cancelAlert() {
        driver.switchTo().alert().dismiss();
    }

    public void waitToAlertPresence() {
        waitExplicit.until(ExpectedConditions.alertIsPresent());
    }

    public void sendkeyToAlert(String value) {
        driver.switchTo().alert().sendKeys(value);
    }

    public String getTextAlert() {
        return driver.switchTo().alert().getText();
    }

    By elementAttribute(String type, String locator) {
        switch (type.toUpperCase()) {
            case "CSS":
                by = By.cssSelector(locator);
                return by;

            default:
                by = By.xpath(locator);
                return by;

        }
    }

    public void clickToElement(String type, String locator) {
        element = driver.findElement(elementAttribute(type, locator));
        element.click();
    }

    public void sendkeyToElement(String type, String locator, String value) {
        element = driver.findElement(By.xpath(locator));
        waitToElementPresence(type, locator);
        element.clear();
        element.sendKeys(value);
    }

    public void clearTextInElement(String type, String locator) {
        element = driver.findElement(elementAttribute(type, locator));
        waitToElementPresence(type, locator);
        element.clear();
    }

    public void waitToElementPresence(String type, String locator) {
        by = elementAttribute(type, locator);
        waitExplicit.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitToElementVisible(String type, String locator) {
        by = elementAttribute(type, locator);
        waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitToElementInvisible(String type, String locator) {
        by = elementAttribute(type, locator);
        waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void waitToElementClickable(String type, String locator) {
        element = driver.findElement(elementAttribute(type, locator));
        waitExplicit.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean isElementHidden(String type, String locator) {
        List<WebElement> elements = driver.findElements(elementAttribute(type, locator));
        return elements.size() == 0;
    }

    public void scrollToElement(String type, String locator) {
        element = driver.findElement(elementAttribute(type, locator));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }
}
