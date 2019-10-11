package Lib.UI.ios;

import Lib.UI.ArticlePageObject;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSArticlePageObject extends ArticlePageObject {

    private static final String
            TITLE = "id:Java (programming Language)",
            FOOTER = "xpath://View page in browser",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later",
            CLOSE_ARTICLE_BUTTON = "id:Back",
            CLOSE_AUTHORIZATION_BUTTON = "xpath://XCUIElementTypeButton[@name=\"places auth close\"]";



    public iOSArticlePageObject(RemoteWebDriver driver) {
        super((AppiumDriver) driver);
        //super(driver);
    }
}
