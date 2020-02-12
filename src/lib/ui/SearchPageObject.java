package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class SearchPageObject extends MainPageObject{

    protected static String
            CLICK_SKIP,
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            CLEAR_BUTTON,
            ARTICLE_BY_DESCRIPTION_AND_TITLE,
            ARTICLE_BY_NAME_APPIUM,
            ARTICLE_BY_NAME_JAVA;

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
        this.waitForElementAndClick(CLICK_SKIP, "\"Cannot find SKIP button", 5);
    }

    public void initSearchInput()
    {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);

    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring);
    }

    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String search_result_xpath = getResultSearchTitleAndDescription(title, description);
        this.waitForElementPresent(search_result_xpath, "\nCannot find search result with Title '" + title + "' and Description '" + description + "'.\n", 5);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    public void clickByArticleWithAppium()
    {
        this.waitForElementAndClick(ARTICLE_BY_NAME_APPIUM, "Cannot find and click search Appium", 10);
    }

    public void clickByArticleWithJava()
    {
        this.waitForElementAndClick(ARTICLE_BY_NAME_JAVA, "Cannot find and click search Java", 10);
    }


    public int getAmmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request ",
                5
        );
        return this.getAmmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public int getAmmountOfFoundArticlesAfterClear()
    {
        this.waitForElementAndClick(
                CLEAR_BUTTON,
                "Cannot find anything by the request ",
                5
        );
        return this.getAmmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmtyResultsLabel()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element.", 10);


    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
    }
}
