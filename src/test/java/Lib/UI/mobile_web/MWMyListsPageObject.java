package Lib.UI.mobile_web;

import Lib.UI.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TRL= "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITTLE}')]";
        ARTICLE_BY_SUBTITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{SUBTITTLE}')]";
        REMOVE_FROM_SAVED_BUTTON="xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITTLE}')]/../../div[contains(@class, 'Watched')]";
    }

    public MWMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
