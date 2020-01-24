package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyListsPageObject extends MainPageObject{

    public static final String
            FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{TITLE}']", //"//*[@text='{TITLE}']",
            ARTICLE_BY_DESCRIPTION_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{DESCRIPTION}']",
            NAME_ARTICLE_TITLE_IN_SAVE_LIST = "//*[@resource-id='org.wikipedia:id/page_list_item_title']";

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


    public MyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);//ВЕРНО
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),//"//*[@text='" + name_of_folder + "']"
                "Cannot find folder by name " + name_of_folder,
                10
        );
    }

    public void waitForArticleToAppearByDescription(String article_description)
    {
        String description_xpath = getSaveArticleXpathByDescription(article_description);
        this.waitForElementPresent(By.xpath(description_xpath), "Cannot find saved article by title " + article_description, 10);
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String title_xpath = getSaveArticleXpathByTitle(article_title);
        this.waitForElementPresent(By.xpath(title_xpath), "Cannot find saved article by title " + article_title, 10);
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String title_xpath = getSaveArticleXpathByDescription(article_title);
        this.waitForElementNotPresent(By.xpath(title_xpath), "Saved article still present with title " + article_title, 10);
    }

    public void swipeByArticleOneToDelete(String article_description)
    {
        this.waitForArticleToAppearByDescription(article_description);
        String description_xpath = getSaveArticleXpathByDescription(article_description);
        this.swipeElementToLeft(
                By.xpath(description_xpath),//"//*[@text='Object-oriented programming language']"
                "Cannot find save article"
        );
        this.waitForArticleToDisappearByTitle(article_description);
    }

    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSaveArticleXpathByTitle(article_title);
        this.swipeElementToLeft(
                By.xpath(article_xpath),
                "Cannot find save article"
        );
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.xpath(NAME_ARTICLE_TITLE_IN_SAVE_LIST), "Cannot find article title on page!", 10);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getSaveArticleXpathByDescription(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 10);
    }

}
