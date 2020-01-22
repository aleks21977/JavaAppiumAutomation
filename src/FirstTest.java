import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }


    @Test
    public void testSearch()
    {
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[contains(@text, 'SKIP')]"),
//                "Cannot find SKIP button",
//                5
//        );

//        WebElement element_SKIP = driver.findElementByXPath("//*[contains(@text, 'SKIP')]");
//        element_SKIP.click();

//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
//                "Cannot find Search Wikipedia input",
//                5
//        );
//
//        MainPageObject.waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
//                "Java",
//                "Cannot find search input",
//                5
//        );
//
//        MainPageObject.waitForElementPresent(
//                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
//                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
//                10
//        );

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult();

    }


    @Test
    public void testCancelSearch()
    {
        WebElement element_SKIP = driver.findElementByXPath("//*[contains(@text, 'SKIP')]");
        element_SKIP.click();

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.className("android.widget.ImageButton"),
                "Cannot find <- to return main page",
                5
        );

        MainPageObject.waitForElementNotPresent(
                By.className("android.widget.ImageButton"),
                "<- is still present on the page",
                5
        );
    }


    @Test
    public void testCompareArticleTitle()
    {
        WebElement element_SKIP = driver.findElementByXPath("//*[contains(@text, 'SKIP')]");
        element_SKIP.click();

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find Java Article",
                5
        );

        WebElement title_element = MainPageObject.waitForElementPresent(
//                By.className("android.view.View"),
                By.xpath("//android.view.View[@resource-id=\"content\"]/android.view.View[1]"),
                "Cannot find article titile",
                10
        );

        String article_title = title_element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );

    }

    @Test
    public void testSwipeArticle()
    {
        WebElement element_SKIP = driver.findElementByXPath("//*[contains(@text, 'SKIP')]");
        element_SKIP.click();

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Appium",
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium' in search",
                5
        );

        MainPageObject.waitForElementPresent(
//                By.className("android.view.View"),
                By.xpath("//android.view.View[@resource-id='content']/android.view.View[1]"),
                "Cannot find article titile",
                10
        );


        MainPageObject.swipeUpToFindElement(
                By.xpath("//*[@text='View page in browser']"),
                "Cannot find the end of the article",
                20
        );


    }

    @Test
    public void testSaveFirstArticleToMyList()
    {
        WebElement element_SKIP = driver.findElementByXPath("//*[contains(@text, 'SKIP')]");
        element_SKIP.click();

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find Java Article",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//android.view.View[@resource-id='content']/android.view.View[1]"),
                "Cannot find article titile",
                10
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/article_menu_bookmark']"),
                "Cannor find button to add article to reading list",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/onboarding_button']"),
                "Cannor find 'GOT IT' tip overlay'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/create_button']"),
                "Cannor find button 'Create new'",
                5
        );

        String name_of_folder = "Learning programming";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/text_input']"),
                name_of_folder,
                "Cannot press OK button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot find button 'Create new'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find Exit button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='NO THANKS']"),
                "Cannot find button 'Create new'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to my list",
                5
        );


        try{Thread.sleep(2000);}  catch (Exception e){}//пауза
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find created folder",
                10
        );

        try{Thread.sleep(2000);}  catch (Exception e){}//пауза
        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find save article"
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot delete saved article",
                5
        );



    }


    @Test
    public void testAmountOfNotEmpty()
    {
        WebElement element_SKIP = driver.findElementByXPath("//*[contains(@text, 'SKIP')]");
        element_SKIP.click();

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        String search_line = "Linkin park discography";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup";
        MainPageObject.waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request " + search_line,
                5
        );

        int ammount_of_search_results = MainPageObject.getAmmountOfElements(By.xpath(search_result_locator));
        //System.out.print(ammount_of_search_results);
        Assert.assertTrue(
                "We found too few results!",
                ammount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch()
    {
        WebElement element_SKIP = driver.findElementByXPath("//*[contains(@text, 'SKIP')]");
        element_SKIP.click();

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        String search_line = "dfsdhtrhsath";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup";
        String empty_result_label = "//*[@text='No results found']";

        MainPageObject.waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result label by the request " + search_line,
                5
        );

        MainPageObject.assertElementNotPresent(
                By.xpath(search_result_locator),
                "We've found some results by request " + search_line
        );
    }

    @Test
    public void testChangeScreenOrientationOnSeacrhResults()
    {
        WebElement element_SKIP = driver.findElementByXPath("//*[contains(@text, 'SKIP')]");
        element_SKIP.click();

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        String search_line = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic search by " + search_line,
                5
        );

        String title_before_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//android.view.View[@resource-id='content']/android.view.View[1]"),
                "text",
                "Cannot find title of article",
                5
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        try{Thread.sleep(2000);}  catch (Exception e){}//пауза
        String title_after_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//android.view.View[@resource-id='content']/android.view.View[1]"),
                "text",
                "Cannot find title of article",
                5
        );

        Assert.assertEquals(
                "Article title have been changed after screen rotation LANDSCAPE",
                title_before_rotation,
                title_after_rotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        try{Thread.sleep(2000);}  catch (Exception e){}//пауза
        String title_after_second_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//android.view.View[@resource-id='content']/android.view.View[1]"),
                "text",
                "Cannot find title of article",
                5
        );

        Assert.assertEquals(
                "Article title have been changed after screen rotation PORTRAIT",
                title_before_rotation,
                title_after_second_rotation
        );


    }


    @Test
    public void testCheckSearchArticleInBackground() {
        WebElement element_SKIP = driver.findElementByXPath("//*[contains(@text, 'SKIP')]");
        element_SKIP.click();

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find Java Article",
                5
        );

        driver.runAppInBackground(3);

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find article after returning from bacground",
                5
        );

    }



}
