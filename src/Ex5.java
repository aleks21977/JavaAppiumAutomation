import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class Ex5 extends CoreTestCase
{
    @Test
    public void testSaveTwoArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.clickSkip();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        //сохраняем первую статью
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();

        String name_of_folder = "Learning programming";
        String article_one_title = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addArticleOneToMyList(name_of_folder);
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickNoThanks();

        //сохраняем вторую статью в ту же папку
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appuim");
        SearchPageObject.clickByArticleWithAppium();
        ArticlePageObject.waitForTitleElement();

        String article_title = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();
        NavigationUI.clickNoThanks();

        //удаляем одну статью
        NavigationUI.clickMyList();
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        try{Thread.sleep(3000);}  catch (Exception e){}//пауза
        MyListsPageObject.openFolderByName(name_of_folder);
        //try{Thread.sleep(2000);}  catch (Exception e){}//пауза
        MyListsPageObject.swipeByArticleToDelete(article_title);

        //проверяем что вторая осталась
        MyListsPageObject.waitForArticleToAppearByTitle(article_one_title);
        String article_title_in_save_list = MyListsPageObject.getArticleTitle();

        //заходим в статью и убеждаемся, что title совпадает
        MyListsPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        String article_title_in_article = ArticlePageObject.getArticleTitle();
        assertEquals(
                "We see unexpected title",
                article_title_in_save_list,
                article_title_in_article
        );
    }
}
