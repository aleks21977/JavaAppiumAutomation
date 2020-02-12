package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MainPageObject;

public class IOSNavigationUI extends MainPageObject{

    private static final String
            CLICK_NO_THANKS = "xpath://*[@text='NO THANKS']",
            MY_LISTS_LINK = "xpath://XCUIElementTypeButton[@name='Saved']";

    public IOSNavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickNoThanks()
    {
        this.waitForElementAndClick(
                CLICK_NO_THANKS,//"//*[@text='NO THANKS']"
                "Cannot find button 'Create new'",
                5
        );
    }


    public void clickMyList()
    {
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot find navigation button to my list",
                5
        );
    }
}
