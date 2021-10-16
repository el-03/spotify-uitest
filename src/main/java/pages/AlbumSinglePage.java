package pages;

import java.io.IOException;

import static locators.AlbumSingleLocator.ALBUM_OR_SINGLE_NAME;
import static locators.AlbumSingleLocator.ARTWORK_ID;
import static utils.ActionUtil.*;

public class AlbumSinglePage {

    public boolean isOnPage(String albumOrSingleName) {
        return waitElementWithText(ALBUM_OR_SINGLE_NAME, albumOrSingleName, 30);
    }

    public boolean presenceOfArtworkImage(String filePath) throws IOException {
        return waitTwoElements(ARTWORK_ID, getElementByIM(filePath), 30);
    }

}
