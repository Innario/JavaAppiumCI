package Lib.UI;

import Lib.Platform;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TRL,
            ARTICLE_BY_TITLE_TRL,
            ARTICLE_BY_SUBTITLE_TPL,
            REMOVE_FROM_SAVED_BUTTON;

    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TRL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TRL.replace("{TITLE}", article_title);
    }

    private static String getRemoveButtonByTittle(String article_tittle)
    {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITTLE}", article_tittle);
    }

    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Can not find folder by name" + name_of_folder,
                5
        );
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresent(article_xpath, "Cannot find saved article by title " + article_title, 15);
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(article_xpath, "Saved article still present with title" + article_title, 15);
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getFolderXpathByName(article_title);


        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElementToLeft(article_xpath, "Cannot find saved article " + article_title);
        } else {
            String remove_locator = getRemoveButtonByTittle(REMOVE_FROM_SAVED_BUTTON);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot find and click button to remove article from list",
                    10
            );
        }
        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
            this.waitForArticleToDisappearByTitle(article_title);
        }

        if (Platform.getInstance().isMW()) {
            driver.navigate().refresh();
        }
    }

        public String getFirstArticleTitle(){
            String title = this.waitForElementAndGetAttribute("id:org.wikipedia:id/view_page_title_text", "text", "Cannot find title of article", 15);
            return title;
        }

    }


