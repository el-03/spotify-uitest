package locators;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public interface AlbumSingleLocator {
    By ARTWORK_ID = MobileBy.id("artwork");
    By ALBUM_OR_SINGLE_NAME = MobileBy.xpath("//android.widget.LinearLayout/*/*/android.widget.TextView");
    By TYPE_ADN_RELEASE_DATE_TEXT_ID = MobileBy.id("metadata");
    By SONG_LIST_TEXT_ID = MobileBy.xpath("//androidx.recyclerview.widget.RecyclerView/*/android.widget.TextView[1]");
}
