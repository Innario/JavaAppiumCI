package Lib.UI.android;

import Lib.UI.NavigationUI;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI {

    static {
        MY_LIST_LINK = "id:Saved";

    }

    public AndroidNavigationUI(RemoteWebDriver driver) {
        super((AppiumDriver) driver);
        //super(driver););
    }
}
