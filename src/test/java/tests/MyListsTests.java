package tests;

import Lib.CoreTestCase;
import Lib.UI.*;
import Lib.UI.factories.ArticlePageObjectFactory;
import Lib.UI.factories.MyListsPageObjectFactory;
import Lib.UI.factories.NavigationUIFactory;
import Lib.UI.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String name_of_folder = "Learning programming";
    private static final String
            login = "Inna2895",
            password = "2895inna";


    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();

        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeArticle();
        }

        if (Platform.getInstance().isMW()) {
            ArticlePageObject.addArticlesToMySaved();
            AuthorizationPageObject AuthPageObject = new AuthorizationPageObject(driver);
            AuthPageObject.clickAuthButton();
            AuthPageObject.enterLoginData(login, password);
            AuthPageObject.submitForm();

            ArticlePageObject.waitForTitleElement();

            assertEquals("We are not on the same page after login", article_title, ArticlePageObject.getArticleTitle());
            ArticlePageObject.addArticlesToMySaved();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticleToMyList(){
        String searchWord = "Java";
        String nameOfList = "My article list";

        String articleTitle = "Java (programming language)";
        String articleDescription = "Object-oriented programming language";
        {
            SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine(searchWord);
            SearchPageObject.clickByArticleWithSubstring(articleDescription);
            ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);


            if (Platform.getInstance().isAndroid()) {
                ArticlePageObject.addArticleToMyList(nameOfList);
            } else if (Platform.getInstance().isIOS()) {
                ArticlePageObject.addArticlesToMySaved();
                ArticlePageObject.closePopUpAuthorization();
                ArticlePageObject.closeArticle();
            } else {
                ArticlePageObject.addArticlesToMySaved();
                AuthorizationPageObject AuthPageObject = new AuthorizationPageObject(driver);
                AuthPageObject.clickAuthButton();
                AuthPageObject.enterLoginData(login, password);
                AuthPageObject.submitForm();
                ArticlePageObject.addArticlesToMySaved();

                ArticlePageObject.waitForTitleElement();

                assertEquals("We are not on the same page after login",
                        articleTitle,
                        ArticlePageObject.getArticleTitle());
            }
        }

        String articleTitle2 = "JavaScript";
        String articleDescription2 = "Programming language";
        {
            SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

            SearchPageObject.initSearchInput();
            if (Platform.getInstance().isAndroid()) {
                SearchPageObject.typeSearchLine(searchWord);
            }
            SearchPageObject.clickByArticleWithSubstring(articleDescription2);
            ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);


            if (Platform.getInstance().isAndroid()) {
                ArticlePageObject.addArticleToMyList(nameOfList);
            } else if (Platform.getInstance().isIOS()) {
                ArticlePageObject.addArticlesToMySaved();
                ArticlePageObject.closeArticle();
            }else {
                ArticlePageObject.addArticlesToMySaved();
                ArticlePageObject.waitForTitleElement();

                assertEquals("We are not on the same page after login",
                        articleTitle2,
                        ArticlePageObject.getArticleTitle());
            }

            ArticlePageObject.closeArticle();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);


        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openFolderByName(nameOfList);
        }

        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            MyListPageObject.swipeByArticleToDelete(articleTitle);
            MyListPageObject.waitForArticleToDisappearByTitle(articleTitle);
            SearchPageObject.clickByArticleWithSubstring(articleDescription2);

            Assert.assertEquals(ArticlePageObject.getArticleTitle(),articleTitle2);
        }else {
            MyListPageObject.swipeByArticleToDelete(articleTitle);
            MyListPageObject.waitForArticleToDisappearByTitle(articleTitle);
            MyListPageObject.waitForArticleToDisappearByTitle(articleTitle2);
            MyListPageObject.openSavedArticleByTittle(articleTitle2);
            ArticlePageObject.waitForTitleElement();
            String opened_article_tittle = ArticlePageObject.getArticleTitle();
            assertEquals(
                    "We see unexpected title!",
                    articleTitle2,
                    opened_article_tittle);
        }
    }

}
