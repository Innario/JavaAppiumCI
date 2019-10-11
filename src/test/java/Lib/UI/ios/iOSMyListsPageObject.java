package Lib.UI.ios;

import Lib.UI.MyListsPageObject;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListsPageObject extends MyListsPageObject {

    static{
            ARTICLE_BY_TITLE_TRL = "xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]";
    }

    public iOSMyListsPageObject(RemoteWebDriver driver) {
        super((AppiumDriver) driver);
        //super(driver);
    }
}
