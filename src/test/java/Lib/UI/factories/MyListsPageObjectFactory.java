package Lib.UI.factories;

import Lib.Platform;
import Lib.UI.MyListsPageObject;
import Lib.UI.android.AndroidMyListsPageObject;
import Lib.UI.ios.iOSMyListsPageObject;
import Lib.UI.mobile_web.MWMyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListsPageObjectFactory {

    public static MyListsPageObject get(RemoteWebDriver driver) {

        if (Platform.getInstance().isAndroid()) {
            return new AndroidMyListsPageObject(driver);
        } else if (Platform.getInstance().isIOS()){
                return new iOSMyListsPageObject(driver);
            } else {
                return new MWMyListsPageObject(driver);
            }
        }
    }

