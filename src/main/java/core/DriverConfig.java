package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverConfig {

    private static WebDriver driver;

    public static boolean closeBrowser = false;

    private DriverConfig() {}

    public static WebDriver initBrowser() {
        if(driver == null) {
            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitBrowser() {
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
