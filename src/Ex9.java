import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class Ex9 extends CoreTestCase {

    @Test
    public void testEx9()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.clickSkip();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java"); //для вывода одного результата поиска ввести Discografia complena
        //try{Thread.sleep(3000);}  catch (Exception e){}//пауза

        String title = "Java (programming language)";
        String description = "Object-oriented programming language";
        SearchPageObject.waitForElementByTitleAndDescription(title, description);

        int count_articles = SearchPageObject.getAmmountOfFoundArticles();
        System.out.print("Found elements >= 3 and = " + count_articles);
        assertTrue(
                "Elements is not found",
                count_articles > 3
        );
    }
}
