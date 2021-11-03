package pages;

import io.appium.java_client.android.AndroidElement;

import java.util.List;

import static drivers.AndroidDriverInstance.androidDriver;
import static locators.SearchLocator.*;
import static utils.ActionUtil.inputElementByKeyboard;
import static utils.ActionUtil.waitImageElement;

public class SearchPage {

    public boolean isOnPage() {
        return waitImageElement(SEARCH_ICON_ACC_ID, SEARCH_ICON_ACTIVE_IMG, 30);
    }

    public void inputSearchBar(String input) {
        inputElementByKeyboard(SEARCH_BAR_INIT_ACC_ID, input);
    }

    public void tapFilter(String filterName) {
        List<AndroidElement> elements = androidDriver.findElements(FILTERS_BUTTON_ID);
        for (AndroidElement element : elements) {
            if (element.getText().equalsIgnoreCase(filterName)) {
                element.click();
            }
        }
    }

    public void tapArtistOnList(String artistName) {
        List<AndroidElement> elements = androidDriver.findElements(ARTIST_NAME_LIST_TEXT_XPATH);
        for (AndroidElement element : elements) {
            if (element.getText().equalsIgnoreCase(artistName)) {
                element.click();
                break;
            }
        }
    }

}
