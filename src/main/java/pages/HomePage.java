package pages;

import java.io.IOException;

import static locators.HomeLocator.*;
import static utils.ActionUtil.*;

public class HomePage {

    public boolean isOnPage() throws IOException {
        return waitTwoElements(HOME_ICON_ACC_ID, getElementByIM(HOME_ICON_ACTIVE_IMG), 30);
    }

    public void tapSearchIcon() {
        tapElement(SEARCH_ICON_ACC_ID);
    }

}
