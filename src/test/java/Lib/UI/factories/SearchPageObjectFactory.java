package Lib.UI.factories;

import Lib.Platform;
import Lib.UI.SearchPageObject;
import Lib.UI.android.AndroidSearchPageObject;
import Lib.UI.ios.iOSSearchPageObject;
import Lib.UI.mobile_web.MWSearchPageObject;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SearchPageObjectFactory {

    public static SearchPageObject get(RemoteWebDriver driver){

        if (Platform.getInstance().isAndroid()) {
            return new AndroidSearchPageObject(driver);
        }else if (Platform.getInstance().isIOS()) {
            return new iOSSearchPageObject(driver);
        } else {
            return new MWSearchPageObject(driver);
        }
    }
}
