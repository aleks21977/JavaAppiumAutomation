import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class Ex5 {

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
        //capabilities.setCapability("app","/Users/perekcicdservicemanager/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }


    @Test
    public void saveTwoArticleToMyList()
    {
        WebElement element_SKIP = driver.findElementByXPath("//*[contains(@text, 'SKIP')]");
        element_SKIP.click();

        //сохраняем первую статью в папку
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find Java Article",
                5
        );

        waitForElementPresent(
                By.xpath("//android.view.View[@resource-id='content']/android.view.View[1]"),
                "Cannot find article titile",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/article_menu_bookmark']"),
                "Cannor find button to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/onboarding_button']"),
                "Cannor find 'GOT IT' tip overlay'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/create_button']"),
                "Cannor find button 'Create new'",
                5
        );

        String name_of_folder = "Learning programming";
        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/text_input']"),
                name_of_folder,
                "Cannot press OK button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot find button 'Create new'",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find Exit button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='NO THANKS']"),
                "Cannot find button 'Create new'",
                5
        );

        //сохраняем вторую статью в ту же папку
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Appuim",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium' in search",
                5
        );

        waitForElementPresent(
                By.xpath("//android.view.View[@resource-id='content']/android.view.View[1]"),
                "Cannot find article titile",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/article_menu_bookmark']"),
                "Cannor find button to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Learning programming']"),
                "Cannor find save list 'Learning programming'",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find Exit button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='NO THANKS']"),
                "Cannot find button 'Create new'",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to my list",
                5
        );

        try{Thread.sleep(2000);}  catch (Exception e){}//пауза
        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find created folder",
                5
        );

        try{Thread.sleep(2000);}  catch (Exception e){}//пауза
        swipeElementToLeft(
                By.xpath("//*[@text='Appium']"),
                "Cannot find save article"
        );

        WebElement title_element_in_save_list = waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                "Cannot find article titile",
                10
        );

        String article_title_in_save_list = title_element_in_save_list.getAttribute("text");
        Assert.assertEquals(
                "Cannot find article Appium",
                "Java (programming language)",
                article_title_in_save_list
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Java (programming language)']"),
                "Cannot find article Appium in list Learning programming",
                5
        );

        WebElement title_element_in_article = waitForElementPresent(
                By.xpath("//android.view.View[@resource-id=\"content\"]/android.view.View[1]"),
                "Cannot find article titile",
                10
        );

        String article_title_in_article = title_element_in_article.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title",
                article_title_in_save_list,
                article_title_in_article
        );
    }


    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element =  waitForElementPresent(by, error_message, 5);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element =  waitForElementPresent(by, error_message, 5);
        element.sendKeys(value);
        return element;
    }

    protected void swipeElementToLeft(By by, String error_massage)
    {
        WebElement element = waitForElementPresent(
                by,
                error_massage,
                10
        );
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();

    }
}
