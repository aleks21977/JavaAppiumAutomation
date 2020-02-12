import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import lib.ui.ios.IOSArticlePageObject;
import lib.ui.ios.IOSMyListsPageObject;
import lib.ui.ios.IOSNavigationUI;
import org.junit.Test;

public class Ex11 extends CoreTestCase
{
    @Test
    public void testSaveTwoArtdicleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line1 = "Java";
        SearchPageObject.typeSearchLine(search_line1);
        SearchPageObject.clickByArticleWithJava();

        //сохраняем первую статью
        IOSArticlePageObject IOSArticlePageObject = new IOSArticlePageObject(driver);
        String name_of_folder = "Learning programming";
        IOSArticlePageObject.addArticleOneToMyList(name_of_folder);
        IOSArticlePageObject.closeArticle();

        //сохраняем вторую статью в ту же папку
        SearchPageObject.initSearchInput();
        IOSArticlePageObject.clearSearchLine();
        String search_line2 = "Appuim";
        SearchPageObject.typeSearchLine(search_line2);
        SearchPageObject.clickByArticleWithAppium();
        IOSArticlePageObject.addArticleToMyList(name_of_folder);
        IOSArticlePageObject.closeArticle();

        //удаляем одну статью
        IOSNavigationUI IOSNavigationUI = new IOSNavigationUI(driver);
        IOSNavigationUI.clickMyList();
        //try{Thread.sleep(2000);}  catch (Exception e){}//пауза
        IOSMyListsPageObject IOSMyListsPageObject = new IOSMyListsPageObject(driver);
        IOSMyListsPageObject.swipeByArticleToDelete();

        //проверяем что вторая осталась
        String article_title_in_save_list = IOSMyListsPageObject.getArticleTitle();
        assertTrue(article_title_in_save_list.contains("Appium"));
    }
}
