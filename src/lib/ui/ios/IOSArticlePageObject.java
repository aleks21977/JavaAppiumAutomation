package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MainPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class IOSArticlePageObject extends MainPageObject{

    private static final String
        TITLE = "xpath://XCUIElementTypeOther[@name='Appium']",
        TITLE1 = "xpath://XCUIElementTypeLink[contains(@name,'Appium')]",
        TITLE2 = "xpath://XCUIElementTypeLink[contains(@name,'Java')]",
        CLEAR_BUTTON = "xpath://XCUIElementTypeButton[@name=\"clear mini\"]",
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
        ADD_TO_MY_LIST_BUTTON1 = "xpath://XCUIElementTypeButton[@name='Share']",
        ADD_TO_MY_LIST_BUTTON2 = "id:Add to reading list",//xpath://XCUIElementTypeButton[@name=Add]",
        ADD_TO_MY_LIST_BUTTON3 = "id:Add",
        GOT_IT_OVERLAY = "xpath://XCUIElementTypeButton[@name='places auth close']",
        CREATE_NEW = "xpath://XCUIElementTypeButton[@name='Add']",
        INPUT_NAME_SAVE_FOLDER = "xpath://XCUIElementTypeButton[@name='clear mini']/../../XCUIElementTypeTextField",
        MY_LIST_OK_BUTTON = "xpath://XCUIElementTypeButton[@name='Create reading list']",
        CLOSE_ARTICLE_BUTTON = "id:Back",
        MY_LIST_NAME_INPUT = "id:Learning programming",
        ADD_TO_READING_LIST_BUTTON = "xpath://XCUIElementTypeButton[contains(@name,'to a reading list?')]",
        UNSAVE_READING_LIST_BUTTON = "id:Add to reading list";


    public IOSArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }


    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page!", 10);
    }

    public WebElement waitForTitleElement1()
    {
        return this.waitForElementPresent(TITLE1, "Cannot find article title on page!", 10);
    }

    public WebElement waitForTitleElement2()
    {
        return this.waitForElementPresent(TITLE2, "Cannot find article title on page!", 10);
    }




    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("name");
    }

    public String getXpathArticle1()
    {
        WebElement title_element = waitForElementPresent(TITLE1, "Cannot find article title on page!", 10);
        return title_element.getAttribute("id");

    }


    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find the end of article",
                20
        );
    }

    public void addArticleOneToMyList(String name_of_folder)
    {
        //кликаем по кнопке добавить в избранное
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON1,
                "Cannor find button to add article to reading list1",
                5
        );


        //кликаем по кнопке добавить в избранное
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON2,
                "Cannor find button to add article to reading list2",
                5
        );


        //кликаем по кнопке добавить в избранное
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON3,
                "Cannor find button to add article to reading list3",
                5
        );


        // вводим название папки Learning programming
        this.waitForElementAndSendKeys(
                INPUT_NAME_SAVE_FOLDER,
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        //кликаем по кнопке ОК
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,//"//*[@text='OK']"
                "Cannot press OK button",
                5
        );

    }

    public void addArticleToMyList(String name_of_folder)
    {
        //кликаем по кнопке добавить в избранное
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON1,
                "Cannor find button to add article to reading list1",
                5
        );


        //кликаем по кнопке добавить в избранное
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON2,
                "Cannor find button to add article to reading list2",
                5
        );


        waitForElementAndClick(
                MY_LIST_NAME_INPUT,
                "Cannor find save list 'Learning programming'",
                5
        );

    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,//"//android.widget.ImageButton[@content-desc='Navigate up']"
                "Cannot close article, cannot find Exit button",
                5
        );
    }


    public void clearSearchLine()
    {
        waitForElementAndClick(
                CLEAR_BUTTON,
                "Cannor find clear button",
                5
        );
    }


}
