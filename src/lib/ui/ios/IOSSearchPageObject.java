package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject
{
    static {
        CLICK_SKIP = "xpath://XCUIElementTypeButton[@name='Skip']";
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]//XCUIElementTypeSearchField";//верно
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeButton[@name='Close']/../../XCUIElementTypeOther[2]/XCUIElementTypeOther[2]//XCUIElementTypeCell";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";//верно
        CLEAR_BUTTON = "xpath://XCUIElementTypeButton[@name='clear mini']";
        ARTICLE_BY_DESCRIPTION_AND_TITLE = "xpath://XCUIElementTypeLink[@name='{TITLE}\n{DESCRIPTION}']";
        ARTICLE_BY_NAME_APPIUM = "xpath://XCUIElementTypeLink[@name='Appium']";
        ARTICLE_BY_NAME_JAVA = "xpath://XCUIElementTypeLink[contains(@name,'Java (programming language)')]";
    }

    public IOSSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
