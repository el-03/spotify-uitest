package locators;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public interface SearchLocator {
    By SEARCH_ICON_ACC_ID = MobileBy.AccessibilityId("Search");
    By SEARCH_BAR_INIT_ACC_ID = MobileBy.AccessibilityId("Search for artists, songs, or podcasts");
    By FILTERS_BUTTON_ID = MobileBy.id("chip_button");
    By ARTIST_NAME_LIST_TEXT_XPATH = MobileBy.xpath("//*[contains(@resource-id, \"text1\")]");

    String SEARCH_ICON_ACTIVE_IMG = "search/search_icon-active.jpg";
}
