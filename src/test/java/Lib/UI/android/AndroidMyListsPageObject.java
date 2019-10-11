package Lib.UI.android;

import Lib.UI.MyListsPageObject;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {

    static{
            FOLDER_BY_NAME_TRL = "xpath://*[@text='{FOLDER_NAME}']";
            ARTICLE_BY_TITLE_TRL = "xpath://*[@text='{TITLE}']";
    }
    public AndroidMyListsPageObject(RemoteWebDriver driver) {
        super((AppiumDriver) driver);
        //super(driver);
    }
}
