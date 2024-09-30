package core;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static core.DriverConfig.*;

public class BaseTest {

    @Rule
    public TestName testName = new TestName();

    @After
    public void endTests() throws IOException {

        TakesScreenshot ss = (TakesScreenshot) initBrowser();
        File archive = ss.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(archive, new File("Target" + File.separator + "screenshot" + File.separator + testName.getMethodName() + ".jpg"));

        if (closeBrowser) {
            quitBrowser();
        }
    }
}
