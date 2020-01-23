package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject{

    public static final String
            FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']",
            ARTICLE_BY_DESCRIPTION_TPL = "//*[@text='{DESCRIPTION}']";

    private static String getFolderXpathByName(String name_of_folder)//ИМЯ ПАПКИ Learning programming
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSaveArticleXpathByTitle(String article_title) //НАЗВАНИЕ СТАТЬИ Java (programming language)
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

    public void waitForArticleToDisappearByTitle(String article_description)
    {
        String description_xpath = getSaveArticleXpathByDescription(article_description);
        this.waitForElementNotPresent(By.xpath(description_xpath), "Saved article still present with title " + article_description, 10);
    }

    public void swipeByArticleToDelete(String article_description)
    {
        this.waitForArticleToAppearByDescription(article_description);
        String description_xpath = getSaveArticleXpathByDescription(article_description);
        this.swipeElementToLeft(
                By.xpath(description_xpath),//"//*[@text='Object-oriented programming language']"
                "Cannot find save article"
        );
        this.waitForArticleToDisappearByTitle(article_description);
    }
}
