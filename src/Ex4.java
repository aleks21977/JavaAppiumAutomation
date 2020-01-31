import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lib.CoreTestCase;
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
import java.util.ArrayList;
import java.util.List;


public class Ex4 extends CoreTestCase
{
    @Test
    public void testCheckWordsInSearch()
    {
        WebElement element_SKIP = driver.findElementByXPath("//*[contains(@text, 'SKIP')]");
        element_SKIP.click();

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                0
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                0
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@class='android.view.ViewGroup']"),
                "Cannot find search Elements",
                2
        );

        //создаем пустой список для хранения в нем названий всех статей
        List<String> name_articles = new ArrayList<String>();
        // получаем все веб элементы содержащие заголовки статей
        List<WebElement> elements = driver.findElements (By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"));
        // получем из всех элементов название статей и заполняем ими ранее созданный список name_articles
        for (WebElement element : elements) {
            String name_article = element.getText();
            name_articles.add(name_article);
        }

        // проверяем что в названиях всех статей есть слово java
        for (String n : name_articles) {
            // для проверки выводим список названий всех статей в консоль
            System.out.print("Name article: " + n + "\n");
            Assert.assertTrue(
                    "Cannot find java",
                    n.toLowerCase().contains("java"));
        }

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                0
        );

        waitForElementAndClick(
                By.className("android.widget.ImageButton"),
                "Cannot find <- to return main page",
                2
        );

        waitForElementNotPresent(
                By.className("android.widget.ImageButton"),
                "<- is still present on the page",
                2
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
        WebElement element =  waitForElementPresent(by, error_message, 3);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element =  waitForElementPresent(by, error_message, 3);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }
}
