package pages;

import static locators.HomeLocator.*;
import static utils.ActionUtil.tapElement;
import static utils.ActionUtil.waitImageElement;

public class HomePage {

    public boolean isOnPage() {
        return waitImageElement(HOME_ICON_ACC_ID, HOME_ICON_ACTIVE_IMG, 30);
    }

    public void tapSearchIcon() {
        tapElement(SEARCH_ICON_ACC_ID);
    }

}
