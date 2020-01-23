package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    private static final String
        TITLE = "//android.view.View[@resource-id='content']/android.view.View[1]",
        DESCRIPTION = "//*[@text='Object-oriented programming language']",
        FOOTER_ELEMENT = "//*[@text='View page in browser']",
        ADD_TO_MY_LIST_BUTTON = "//*[@resource-id='org.wikipedia:id/article_menu_bookmark']",
        GOT_IT_OVERLAY = "//*[@resource-id='org.wikipedia:id/onboarding_button']",
        CREATE_NEW = "//*[@resource-id='org.wikipedia:id/create_button']",
        INPUT_NAME_SAVE_FOLDER = "//*[@resource-id='org.wikipedia:id/text_input']",
        MY_LIST_OK_BUTTON = "//*[@text='OK']",
        CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }


    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.xpath(TITLE), "Cannot find article title on page!", 10);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public WebElement waitForDescriptionElement()
    {
        return this.waitForElementPresent(By.xpath(DESCRIPTION), "Cannot find article description on page!", 10);
    }

    public String getArticleDescription()
    {
        WebElement description_element = waitForDescriptionElement();
        return description_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                20
        );
    }

    public void addArticleToMyList(String name_of_folder)
    {
        //кликаем по кнопке добавить в избранное
        this.waitForElementAndClick(
                By.xpath(ADD_TO_MY_LIST_BUTTON),//"//*[@resource-id='org.wikipedia:id/article_menu_bookmark']"
                "Cannor find button to add article to reading list",
                5
        );

        //кликаем по кнопке попапа GOT IT
        this.waitForElementAndClick(
                By.xpath(GOT_IT_OVERLAY), //"//*[@resource-id='org.wikipedia:id/onboarding_button']"
                "Cannor find 'GOT IT' tip overlay'",
                5
        );

        //кликаем по кнопке Create new
        this.waitForElementAndClick(
                By.xpath(CREATE_NEW),//"//*[@resource-id='org.wikipedia:id/create_button']"
                "Cannor find button 'Create new'",
                5
        );

        // вводим название папки Learning programming
        this.waitForElementAndSendKeys(
                By.xpath(INPUT_NAME_SAVE_FOLDER),//'//*[@resource-id='org.wikipedia:id/text_input']"
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        //кликаем по кнопке ОК
        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),//"//*[@text='OK']"
                "Cannot press OK button",
                5
        );

    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),//"//android.widget.ImageButton[@content-desc='Navigate up']"
                "Cannot close article, cannot find Exit button",
                5
        );
    }

}
