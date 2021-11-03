package pages;

import static locators.AlbumSingleLocator.ALBUM_OR_SINGLE_NAME;
import static locators.AlbumSingleLocator.ARTWORK_ID;
import static utils.ActionUtil.waitElementWithText;
import static utils.ActionUtil.waitImageElement;

public class AlbumSinglePage {

    public boolean isOnPage(String albumOrSingleName) {
        return waitElementWithText(ALBUM_OR_SINGLE_NAME, albumOrSingleName, 30);
    }

    public boolean presenceOfArtworkImage(String filePath) {
        return waitImageElement(ARTWORK_ID, filePath, 30);
    }

}
