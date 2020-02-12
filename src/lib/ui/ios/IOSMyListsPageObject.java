package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MainPageObject;
import org.openqa.selenium.WebElement;

public class IOSMyListsPageObject extends MainPageObject {

    public static final String
            FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{TITLE}']", //"//*[@text='{TITLE}']",
            ARTICLE_BY_DESCRIPTION_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{DESCRIPTION}']",
            TEXT_APPIUM_IN_SAVE_LIST = "xpath://XCUIElementTypeLink[contains(@name,'Appium')]",
            NAME_FOR_IOS = "xpath://XCUIElementTypeLink[contains(@name,'Java (programming language)')]",
            DELETE_ARTICLE_BUTTON = "xpath://XCUIElementTypeButton[@name='Unsave']";

    private static String getFolderXpathByName(String name_of_folder)//ИМЯ ПАПКИ Learning programming
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSaveArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getSaveArticleXpathByDescription(String article_description) //ОПИСАНИЕ СТАТЬИ Object-oriented programming language
    {
        return ARTICLE_BY_DESCRIPTION_TPL.replace("{DESCRIPTION}", article_description);
    }


    public IOSMyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);//ВЕРНО
        this.waitForElementAndClick(
                folder_name_xpath,//"//*[@text='" + name_of_folder + "']"
                "Cannot find folder by name " + name_of_folder,
                10
        );
    }

    public void waitForArticleToAppearByDescription(String article_description)
    {
        String description_xpath = getSaveArticleXpathByDescription(article_description);
        this.waitForElementPresent(description_xpath, "Cannot find saved article by title " + article_description, 10);
    }

    public void waitForArticleToAppearByTitle(String title_xpath)
    {
        ///String title_xpath = getSaveArticleXpathByTitle(article_title);
        this.waitForElementPresent(title_xpath, "Cannot find saved article by title ", 10);
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String title_xpath = getSaveArticleXpathByDescription(article_title);
        this.waitForElementNotPresent(title_xpath, "Saved article still present with title " + article_title, 10);
    }

    public void swipeByArticleOneToDelete(String article_description)
    {
        this.waitForArticleToAppearByDescription(article_description);
        String description_xpath = getSaveArticleXpathByDescription(article_description);
        this.swipeElementToLeft(
                description_xpath,//"//*[@text='Object-oriented programming language']"
                "Cannot find save article"
        );
        this.waitForArticleToDisappearByTitle(article_description);
    }


    public void swipeByArticleToDelete()
    {
        String article_xpath = NAME_FOR_IOS;
        this.waitForArticleToAppearByTitle(article_xpath);
        this.swipeElementToLeft(
                article_xpath,
                "Cannot find save article"
        );
        this.closeAlertForDeleteArticle();
        //this.waitForArticleToDisappearByTitle(article_xpath);
    }

    public void closeAlertForDeleteArticle()
    {
        this.waitForElementAndClick(DELETE_ARTICLE_BUTTON, "Cannot find delete button", 10);
    }



    public WebElement waitForLableElement()
    {
        return this.waitForElementPresent(TEXT_APPIUM_IN_SAVE_LIST, "Cannot find article with Appium!", 10);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForLableElement();
        return title_element.getAttribute("label");
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getSaveArticleXpathByDescription(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }



}
