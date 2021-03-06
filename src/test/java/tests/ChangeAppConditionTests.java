package tests;

import Lib.CoreTestCase;
import Lib.UI.ArticlePageObject;
import Lib.UI.SearchPageObject;
import Lib.UI.factories.ArticlePageObjectFactory;
import Lib.UI.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeOrientationOnSearchResults() {
        if (Platform.getInstance().isMW()) {
            return;
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();

        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle();

        assertEquals(
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        if (Platform.getInstance().isMW()) {
            return;
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");

        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

}
