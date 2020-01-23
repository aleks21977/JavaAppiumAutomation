import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class Ex3 extends CoreTestCase
{
    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        try{Thread.sleep(3000);}  catch (Exception e){}//пауза

        int count_articles = SearchPageObject.getAmmountOfFoundArticles();
        System.out.print("Количество найденных элементов: " + count_articles);
        assertTrue(
                "Elements is not found",
                count_articles > 0
        );

        int count_articles_after_clear = SearchPageObject.getAmmountOfFoundArticlesAfterClear();
        System.out.print("\nКоличество найденных элементов: " + count_articles_after_clear);
        assertTrue(
                "List not clear",
                count_articles_after_clear == 0
        );
    }
}
