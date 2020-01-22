package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{

    private static final String
            CLICK_CKIP = "//*[contains(@text, 'SKIP')]",
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_RESULT = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void initSearchInput()
    {
        this.waitForElementAndClick(By.xpath(CLICK_CKIP), "\"Cannot find SKIP button", 5);
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);

    }

    public void waitForSearchResult()
    {
        this.waitForElementPresent(By.xpath(SEARCH_RESULT), "Cannot find search result");
    }
}
