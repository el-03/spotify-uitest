package pages;

import static locators.ArtistLocator.ARTIST_NAME_TEXT_XPATH;
import static locators.ArtistLocator.SEE_DISCOGRAPHY_BUTTON_XPATH;
import static utils.ActionUtil.scrollThenTap;
import static utils.ActionUtil.waitElementWithText;

public class ArtistPage {

    public boolean isOnPage(String artistName) {
        return waitElementWithText(ARTIST_NAME_TEXT_XPATH, artistName, 30);
    }

    public void tapSeeDiscoveryButton() {
        scrollThenTap(SEE_DISCOGRAPHY_BUTTON_XPATH);
    }

}
