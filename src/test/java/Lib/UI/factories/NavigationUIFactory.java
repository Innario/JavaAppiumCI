package Lib.UI.factories;

import Lib.Platform;
import Lib.UI.ArticlePageObject;
import Lib.UI.NavigationUI;
import Lib.UI.android.AndroidArticlePageObject;
import Lib.UI.android.AndroidNavigationUI;
import Lib.UI.ios.iOSArticlePageObject;
import Lib.UI.ios.iOSNavigationUI;
import Lib.UI.mobile_web.MWNavigationUI;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUIFactory {

    public static NavigationUI get(RemoteWebDriver driver){
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUI(driver);
        }else if (Platform.getInstance().isIOS()) {
            return new iOSNavigationUI(driver);
        }else {
            return new MWNavigationUI(driver);
        }
    }
}
