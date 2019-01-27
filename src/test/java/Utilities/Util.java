
package Utilities;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

public abstract class Util extends Environment_Config{
    protected static WebDriver driver = null;
    protected static int defaultTimeout = 40;

    protected static void sleep(int waitValue) {
        System.out.println("Sleeping for '" + waitValue + "' half seconds");
        try {
            Thread.sleep(waitValue * 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("SameParameterValue")
    protected static void navigate(String URL) {
        System.out.println("Navigating to URL: " + URL);
        driver.get(URL);
    }


    protected static boolean isElementPresent(WebElement element) {
        System.out.println("Checking to see if Element is Present :" + element.toString());
        try {
            element.isDisplayed();
            System.out.println("Checking to see if Element is Present : Element Found !");
            return true;
        } catch (Exception e) {
            System.out.println("Checking to see if Element is Present : Element Not Found !");
            return false;
        }
    }


    protected static void WaitForElement(WebElement element) {
        Boolean found=false;
        try {
            for (int i = 0; i < defaultTimeout ; i++) {
                try {
                    if(i==0)
                        System.out.println("Waiting for Element : Element : " + element.toString());
                    found=element.isDisplayed();
                    if(found){
                        found = true;
                        break;
                    }
                    else
                        sleep(1);
                } catch (Exception e) {
                    sleep(1);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while Waiting for Element, Exception :" + e.getMessage());
        }
        Assert.assertTrue(found, "WaitForElement : Element Found ");
    }

    protected static WebElement ElementByXPath(String xpath) {

//        WaitForElement(driver.findElement(By.xpath(xpath)));
//        return driver.findElement(By.xpath(xpath));

        WebElement element = null;

        Boolean found=false;
        System.out.println("Waiting for Element By XPath:" + xpath + ", a maximum of "+defaultTimeout+" seconds");

        try {
            for (int i = 0; i < defaultTimeout ; i++) {
                try {
                    element = driver.findElement(By.xpath(xpath));
                    found=element.isDisplayed();
//                    found = driver.findElement(By.xpath(xpath)).isDisplayed();
                    if(found){
                        found = true;
                        break;
                    }
                    else
                        sleep(1);
                } catch (Exception e) {
                    sleep(1);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while Waiting for Element, Exception :" + e.getMessage());
        }

        return element;
    }

    protected static void ClickElementByXPath(String xpath) {
        boolean found = false;
        System.out.println("Clicking on Element with Xpath:" + xpath);
        for (int i = defaultTimeout; i > 0; i--) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 1);
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpath))));
                driver.findElement(By.xpath(xpath)).click();
                found = true;
                break;
            } catch (Exception e) {
                sleep(1);
                Log("Sleeping for 1 Second");
            }
        }
        Assert.assertTrue(found==true, "ClickElementByXPath : Element NOT Found");
    }

    protected static void AssertDisplayed(WebElement element)
    {
        Log("Verifying Element is Displayed :" + element.toString());
        WaitForElement(element);
        element.isDisplayed();
    }

    protected static void click(WebElement element)
    {
        Boolean found=false;
        WaitForElement(element);
        System.out.println("Clicking on Element :" + element.toString());
        for (int i = 0; i <defaultTimeout ; i++) {
            try {
                element.click();
                found=true;
                break;
            } catch (Exception e) {
                sleep(1);
            }
        }
        Assert.assertTrue(found==true, "Element '" + element.toString() + "'");
    }


    //This method takes a picture of the current screen when called and saves in the Screenshots folder
    protected static void captureScreenshot() {
        try {
            String filePath = "C:\\Selenium\\IntelliJ_Projects\\LST2\\target\\screenshots";
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            Date date = new Date();
            String date1 = dateFormat.format(date);
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(filePath + "\\screenshot_" + date1 + ".png"));

            System.out.println("***Placed screen shot in " + filePath
                    + "\\screenshot_" + date1 + ".png" + " ***");
            Reporter.log("<a href='" + filePath + "\\screenshot_" + date1
                    + ".png" + "'>screenshot</a>");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static void WaitForPageRefresh()
    {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        for (int i=0; i<defaultTimeout; i++){
            if (js.executeScript("return document.readyState").toString().equals("complete")){
                break;
            }
            sleep(1);
        }
        sleep(6);
    }

    protected static void Log(String text)
    {
        System.out.println(text);
    }


    protected static void sendKeysEnter(WebElement element, String text) {
        Log("Entering Text'"+text+"' in ths element '"+element+"'");
        WaitForElement(element);
        element.clear();
        element.sendKeys(text);
        element.sendKeys(Keys.ENTER);
        WaitForPageRefresh();
    }

}

