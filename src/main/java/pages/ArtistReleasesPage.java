package pages;

import io.appium.java_client.android.AndroidElement;

import java.util.List;

import static drivers.AndroidDriverInstance.androidDriver;
import static locators.ArtistReleasesLocator.ALBUM_SINGLE_LIST_TEXT_XPATH;
import static locators.ArtistReleasesLocator.PAGE_TITLE_TEXT_ID;
import static utils.ActionUtil.waitElementWithText;

public class ArtistReleasesPage {

    public boolean isOnPage() {
        return waitElementWithText(PAGE_TITLE_TEXT_ID, "Releases", 30);
    }

    public void tapAlbumOrSingle(String albumOrSingleName) {
        List<AndroidElement> elements = androidDriver.findElements(ALBUM_SINGLE_LIST_TEXT_XPATH);
        for (AndroidElement element : elements) {
            if (element.getText().equalsIgnoreCase(albumOrSingleName)) {
                element.click();
                break;
            }
        }
    }

}
