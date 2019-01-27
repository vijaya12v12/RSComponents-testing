
package Utilities;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

public class ServiceHooks extends Util {


    @Before
    public void initializeTest()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\users\\vijji\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver =new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                Log("Waiting for 5 Seconds before Capturing the Screenshot");
                sleep(5);
                captureScreenshot();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Step 4- Close Driver
        driver.close();

        //Step 5- Quit Driver
        driver.quit();
    }
}
