package Insta;




import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Instagram
{
	public static AndroidDriver driver;
	public static WebDriverWait wait;
	public static Set<String> productName = new HashSet<String>();;
	public static List<WebElement> prodCount;
	public static void impliciteWait(int time)
	{
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	public static void expliciteWait(int time,WebElement element)
	{
		wait=new WebDriverWait(driver,time);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public static void launch(String appPackage,String appActivity) throws MalformedURLException
	{	
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "10");
		cap.setCapability("deviceName", "emulator-5554");
		cap.setCapability("appPackage", appPackage);
		cap.setCapability("appActivity", appActivity);
		driver= new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),cap);
		impliciteWait(10);
		System.out.println("Appliation is launched");
		test();
	}
	private static void test() {
		
		driver.findElementByXPath("//android.widget.TextView[@text='Continue as Suraj Sutar']").click();
		WebElement image = driver.findElementById("com.instagram.android:id/row_feed_photo_people_tagging");
		int center_X=image.getLocation().getX()+(image.getSize().width/2);
		int center_Y=image.getLocation().getY()+(image.getSize().height/2);

			MultiTouchAction multiTouchAction = new MultiTouchAction(driver);
			
			TouchAction zoomOut = new TouchAction(driver);
			zoomOut.press(PointOption.point(center_X,center_Y-10)).moveTo(PointOption.point(center_X,center_Y-200)).release();
			
			TouchAction zoomIn = new TouchAction(driver);
			zoomIn.press(PointOption.point(center_X,center_Y+10)).moveTo(PointOption.point(center_X,center_Y+200)).release();
			
			multiTouchAction.add(zoomOut).add(zoomIn).perform();
	}
	public static void main( String[] args ) throws MalformedURLException
	{
		launch("com.instagram.android", "com.instagram.android.activity.MainTabActivity");
	}
}
