package ggtest;

import common.Fixtures;
import common.TestConfig;
import models.EnvironmentData;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.HashMap;

public class BaseTest {
    public static HashMap<String, String> environmentUrls;
    public static HashMap<String, HashMap<String, String>> existingUsers;
    WebDriver browser;
    String appURL;

    @BeforeClass()
    public void setConfiguration() {
        initEnvironment();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeTestMethods(Method method) {
        appURL = environmentUrls.get("base_url");
        browser = Fixtures.SetUp.initBrowser(appURL);
    }

    @AfterMethod(alwaysRun = true)
    public void afterTestMethods() {
        Fixtures.TearDown.close(browser);
    }

    void initEnvironment() {
        EnvironmentData environmentData = new EnvironmentData(TestConfig.getEnvironment());
        environmentUrls = environmentData.environmentUrls;
        existingUsers = environmentData.existingUsers;
        appURL = environmentUrls.get("base_url");
    }
}
