package locators;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public interface HomeLocator {
    By HOME_ICON_ACC_ID = MobileBy.AccessibilityId("Home");
    By SEARCH_ICON_ACC_ID = MobileBy.AccessibilityId("Search");

    String HOME_ICON_ACTIVE_IMG = "home/home_icon-active.jpg";
}
