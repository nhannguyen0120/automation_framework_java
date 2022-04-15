package common;

public class TestConfig {

    public static String getEnvironment() {
        String envEnvironment = System.getProperty("testEnvironment");
        return (envEnvironment != null) ? envEnvironment : "test";
    }

    public static Browser getBrowser() {
        String envBrowser = System.getProperty("testBrowser");
        envBrowser = (envBrowser != null) ? envBrowser : "chrome";

        return switch (envBrowser.toLowerCase()) {
            case "firefox" -> Browser.FIREFOX;
            case "ie" -> Browser.IE;
            case "edge" -> Browser.EDGE;
            case "safari" -> Browser.SAFARI;
            default -> Browser.CHROME;
        };
    }

    public enum Browser {FIREFOX, CHROME, IE, EDGE, SAFARI}
}

