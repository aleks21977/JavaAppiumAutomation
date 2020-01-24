package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{

    private static final String
            CLICK_SKIP = "//*[contains(@text, 'SKIP')]",
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_CANCEL_BUTTON = "android.widget.ImageButton",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']",
            CLEAR_BUTTON = "//*[@resource-id='org.wikipedia:id/search_close_btn']",
            ARTICLE_BY_DESCRIPTION_AND_TITLE = "//android.view.ViewGroup/android.widget.TextView[@text='{TITLE}']/../android.widget.TextView[@text='{DESCRIPTION}']";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchTitleAndDescription(String title, String description)
    {
        return ARTICLE_BY_DESCRIPTION_AND_TITLE.replace("{TITLE}", title).replace( "{DESCRIPTION}", description);
    }
    /* TEMPLATES METHODS */

    public void clickSkip()
    {
        this.waitForElementAndClick(By.xpath(CLICK_SKIP), "\"Cannot find SKIP button", 5);
    }

    public void initSearchInput()
    {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.className(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(By.className(SEARCH_CANCEL_BUTTON), "Search cancel button is still present", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(By.className(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);

    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring);
    }

    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String search_result_xpath = getResultSearchTitleAndDescription(title, description);
        this.waitForElementPresent(By.xpath(search_result_xpath), "\nCannot find search result with Title '" + title + "' and Description '" + description + "'.\n");
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 10);
    }

    public void clickByArticleWithAppium()
    {
        this.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"), "Cannot find and click search Appium", 10);
    }

    public int getAmmountOfFoundArticles()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),//"//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup"
                "Cannot find anything by the request ",
                5
        );
        return this.getAmmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public int getAmmountOfFoundArticlesAfterClear()
    {
        this.waitForElementAndClick(
                By.xpath(CLEAR_BUTTON),
                "Cannot find anything by the request ",
                5
        );
        return this.getAmmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmtyResultsLabel()
    {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot find empty result element.", 5);


    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed not to find any results");
    }
}
