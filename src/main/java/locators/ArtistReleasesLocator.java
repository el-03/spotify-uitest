package locators;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public interface ArtistReleasesLocator {
    By PAGE_TITLE_TEXT_ID = MobileBy.id("glue_toolbar_title");
    By ALBUM_SINGLE_LIST_TEXT_XPATH = MobileBy.xpath("//*[contains(@resource-id, \"text1\")]");
    By ALBUM_SINGLE_RELEASE_DATE_LIST_TEXT_XPATH = MobileBy.xpath("//*[contains(@resource-id, \"text2\")]");
}
