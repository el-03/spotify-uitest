package locators;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public interface ArtistLocator {
    By ARTIST_AND_POPULAR_SONGS_LIST_TEXT_ID = MobileBy.id("title");
    By ARTIST_NAME_TEXT_XPATH = MobileBy.xpath("//android.widget.LinearLayout/*/*/android.widget.TextView");
    By MONTHLY_LISTENER_TEXT_ID = MobileBy.id("metadata");
    By SEE_DISCOGRAPHY_BUTTON_XPATH = MobileBy.xpath("//android.widget.Button[@text=\"See discography\"]");
}
