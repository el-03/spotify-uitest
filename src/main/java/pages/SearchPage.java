package pages;

import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;

import static drivers.AndroidDriverInstance.androidDriver;
import static locators.ArtistReleasesLocator.ALBUM_SINGLE_LIST_TEXT_XPATH;
import static locators.SearchLocator.*;
import static utils.ActionUtil.*;

public class SearchPage {

    public boolean isOnPage() throws IOException {
        return waitTwoElements(SEARCH_ICON_ACC_ID, getElementByIM(SEARCH_ICON_ACTIVE_IMG), 30);
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
