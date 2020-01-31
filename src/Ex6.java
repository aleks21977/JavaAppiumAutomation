import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class Ex6 extends CoreTestCase
{
    @Test
    public void testAssertTitle()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.clickSkip();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        //try{Thread.sleep(3000);}  catch (Exception e){}//пауза
        int ammount_need_title = ArticlePageObject.getAmmountOfElements();
        //System.out.print("Found article title =" + ammount_need_title);
        assertTrue(
                "Ammount article title != 1",
                ammount_need_title == 1
        );
    }
}
