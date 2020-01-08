import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","E:\\!Software_Testing\\!!GitHub\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void firstTest()
    {
        WebElement element_SKIP = driver.findElementByXPath("//*[contains(@text, 'SKIP')]");
        element_SKIP.click();

        waitForElementByXpathAndClick(
                "//*[contains(@text, 'Search Wikipedia')]",
                "Cannot find Search Wikipedia input",
                5
        );
        waitForElementByXpathAndSendKeys(
                "//*[contains(@text, 'Search Wikipedia')]",
                "Java",
                "Cannot find search input",
                5
        );
        waitForElementPresentByXpath(
                "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']",
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                10
        );

    }

    @Test
    public void testCancelSearch()
    {
        WebElement element_SKIP = driver.findElementByXPath("//*[contains(@text, 'SKIP')]");
        element_SKIP.click();

        waitForElementByIdAndClick(
                "org.wikipedia:id/search_container",
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementByXpathAndSendKeys(
                "//*[contains(@text, 'Search Wikipedia')]",
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementByClassNameAndClick(
                "android.widget.ImageButton",
                "Cannot find <- to return main page",
                5
        );

        waitForElementNotPresent(
                "android.widget.ImageButton",
                "<- is still present on the page",
                5
        );
    }



    private WebElement waitForElementPresentByXpath(String xpath, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresentByXpath(String xpath, String error_message)
    {
        return waitForElementPresentByXpath(xpath, error_message, 5);
    }

    private WebElement waitForElementByXpathAndClick(String xpath, String error_message, long timeoutInSeconds)
    {
        WebElement element =  waitForElementPresentByXpath(xpath, error_message, 5);
        element.click();
        return element;
    }

    private WebElement waitForElementByXpathAndSendKeys(String xpath, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element =  waitForElementPresentByXpath(xpath, error_message, 5);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementPresentById(String Id, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.id(Id);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresentByClassName(String className, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.className(className);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementByIdAndClick(String Id, String error_message, long timeoutInSeconds)
    {
        WebElement element =  waitForElementPresentById(Id, error_message, 5);
        element.click();
        return element;
    }

    private WebElement waitForElementByClassNameAndClick(String className, String error_message, long timeoutInSeconds)
    {
        WebElement element =  waitForElementPresentByClassName(className, error_message, 5);
        element.click();
        return element;
    }

    private boolean waitForElementNotPresent(String className, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.className(className);
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
}
