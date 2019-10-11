package Lib.UI.ios;

import Lib.UI.NavigationUI;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSNavigationUI extends NavigationUI {

     static {
         MY_LIST_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']";

     }

    public iOSNavigationUI(RemoteWebDriver driver) {
        super((AppiumDriver) driver);
        //super(driver);;
    }



}
