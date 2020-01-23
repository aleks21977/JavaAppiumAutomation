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
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        //try{Thread.sleep(5000);}  catch (Exception e){}//пауза
        int ammount_need_title = ArticlePageObject.getAmmountOfElements();
        //System.out.print(ammount_need_title);
        assertTrue(
                "Ammount article title != 1",
                ammount_need_title == 1
        );
    }
}
