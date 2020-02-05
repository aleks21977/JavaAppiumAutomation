package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase
{

    @Test
    public void testSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.clickSkip();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }


    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.clickSkip();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }


    @Test
    public void testAmountOfNotEmpty()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.clickSkip();
        SearchPageObject.initSearchInput();
        String search_line = "Linkin park discography";
        SearchPageObject.typeSearchLine(search_line);
        int ammount_of_search_results = SearchPageObject.getAmmountOfFoundArticles();

        assertTrue(
                "We found too few results!",
                ammount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.clickSkip();
        SearchPageObject.initSearchInput();
        String search_line = "dfsdhtrhsath";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmtyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();


    }
}
