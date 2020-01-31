import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lib.CoreTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Ex7 extends CoreTestCase
{
    @Test
    public void testCheckLandscapeOrientation()
    {
        WebElement element_SKIP = driver.findElementByXPath("//*[contains(@text, 'SKIP')]");
        element_SKIP.click();

        //driver.rotate(ScreenOrientation.LANDSCAPE);
        ScreenOrientation currentOrientation = driver.getOrientation();
        if (currentOrientation != ScreenOrientation.PORTRAIT){
            driver.rotate(ScreenOrientation.PORTRAIT);
        }

        try{Thread.sleep(2000);}  catch (Exception e){}//пауза
    }
}
