package Lib.UI;

import Lib.Platform;
import Lib.UI.android.AndroidSearchPageObject;
import Lib.UI.ios.iOSSearchPageObject;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);

    }

     protected static  String
            TITLE,
            FOOTER,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            OPTIONS_REMOVE_FROM_READING_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            MY_LIST_ITEM,
            CLOSE_ARTICLE_BUTTON;

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private static String getListXpathByName(String name_of_folder) {
        return MY_LIST_ITEM.replace("{LIST_NAME}", name_of_folder);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    public String getTitleElementText() {
        WebElement element = driver.findElement(By.id(TITLE));
        return element.getAttribute("text");
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        }else if (Platform.getInstance().isIOS()){
            return title_element.getAttribute("name");
        }else {
            return title_element.getText();
        }
    }

    public void swipeToFooter() {

        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER, "Cannot find the end of article", 40);
        }else if (Platform.getInstance().isIOS()) {
            this.SwipeUpTillElementAppear(FOOTER, "Cannot find the end of article", 40);
        }else {
            this.scrollWebPageTillElementVisible(FOOTER_ELEMENT, "Cannot find find the end of article", 40);
        }


    }


    public void addArticleToMyList(String name_of_folder) {

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Can not find element",
                5

        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Can not find element 'Add to reading list'",
                5

        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Can not find element 'GOT IT'",
                5

        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Can not find input to set name of articles folder",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot find search input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Can not find element 'OK'",
                5

        );

    }

    public void addArticleToMyExistingList(String name_of_folder) {

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Can not find element",
                5

        );
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Can not find element 'Add to reading list'",
                5

        );
        String listXpath = getListXpathByName(name_of_folder);
        this.waitForElementAndClick(
                listXpath,
                "Can not find element " + name_of_folder,
                5
        );
    }


    public void addArticlesToMySaved(){

        if (Platform.getInstance().isMW()) {
            this.removeArticleFromSavedIfAdded();
        }
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
    }

    public void removeArticleFromSavedIfAdded()
    {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_READING_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_READING_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    1
            );
            this.waitForElementPresent(
                    OPTIONS_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find button to add to saved list after removing is from this list before",
                    0
            );
        }
    }

    public void closeArticle() {

        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Can not find element 'X'",
                    5
            );
        } else {
            System.out.println("Method closeArticle() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void closePopUpAuthorization() {
        this.waitForElementAndClick(
                CLOSE_AUTHORIZATION_BUTTON,
                "Can not find element 'X'",
                5
        );
    }

}
