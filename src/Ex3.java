import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class Ex3 extends CoreTestCase
{
    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.clickSkip();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        try{Thread.sleep(3000);}  catch (Exception e){}//пауза

        int count_articles = SearchPageObject.getAmmountOfFoundArticles();
        System.out.print("Found elements: " + count_articles);
        assertTrue(
                "Elements is not found",
                count_articles > 0
        );

        int count_articles_after_clear = SearchPageObject.getAmmountOfFoundArticlesAfterClear();
        System.out.print("\nFound elements after clear: " + count_articles_after_clear);
        assertTrue(
                "List not clear",
                count_articles_after_clear == 0
        );
    }
}
