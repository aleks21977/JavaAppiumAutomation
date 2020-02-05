package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{

    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.clickSkip();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        //String article_title = ArticlePageObject.getArticleTitle();
        String article_description = ArticlePageObject.getArticleDescription();
        String name_of_folder = "Learning programming";

        ArticlePageObject.addArticleOneToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickNoThanks();
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        try{Thread.sleep(3000);}  catch (Exception e){}//пауза
        MyListsPageObject.openFolderByName(name_of_folder);
        //try{Thread.sleep(2000);}  catch (Exception e){}//пауза
        MyListsPageObject.swipeByArticleOneToDelete(article_description);
    }
}
