package pages;

import static locators.ArtistLocator.*;
import static utils.ActionUtil.*;

public class ArtistPage {

    public boolean isOnPage(String artistName) {
        return waitElementWithText(ARTIST_NAME_TEXT_XPATH, artistName, 30);
    }

    public void tapSeeDiscoveryButton() {
        scrollThenTap(SEE_DISCOGRAPHY_BUTTON_XPATH);
    }

}
