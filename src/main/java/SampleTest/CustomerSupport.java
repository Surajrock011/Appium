package SampleTest;

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
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class CustomerSupport
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
	public static void display(WebElement ele)
	{
		if(ele.isDisplayed() && !(ele.getAttribute("text").isEmpty()))
		{
			System.out.println(ele.getText() + " is displayed");
		}
		else if(ele.isDisplayed())
		{
			System.out.println("Element is displayed");
		}
		else
		{
			System.out.println("Element is not displayed");
		}


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
		System.out.println("Appliation is launched \n");
		WebElement signupBtn = driver.findElementById("com.husqvarna.connect.qa:id/landing_sign_up_btn");
		display(signupBtn);
		WebElement hqLogo = driver.findElementByXPath("//android.widget.ImageView[@index='0']");
		display(hqLogo);
		WebElement connectTxtBox = driver.findElementByXPath("//*[@text='HUSQVARNA CONNECT']");
		display(connectTxtBox);
		WebElement loginBtn = driver.findElementById("com.husqvarna.connect.qa:id/landing_login_btn");
		display(loginBtn);
		loginBtn.click();
		System.out.println("\nLogin Page : ");
		WebElement emailId = driver.findElementById("com.husqvarna.connect.qa:id/login_email_edit_text");
		emailId.sendKeys("surajrock011@gmail.com");
		WebElement passward = driver.findElementById("com.husqvarna.connect.qa:id/login_password_edit_text");
		passward.sendKeys("123456");
		WebElement loginBtn1 = driver.findElementById("com.husqvarna.connect.qa:id/login_btn");
		loginBtn1.click();
		
		System.out.println("Home Screen \n");
		try
		{	
			if (driver.findElementById("com.husqvarna.connect.qa:id/snackbar_text").isDisplayed())
			{
				System.out.println("Wrong credentials,Please check credentials and try again");
			} 
		}
		catch(Exception e)
		{
			WebElement allowdBtn = driver.findElementById("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
			display(allowdBtn);
			allowdBtn.click();
//			WebElement denyBtn = driver.findElementById("android:id/button2");
			WebElement denyBtn = driver.findElementByXPath("//android.widget.Button[@text='DENY']");
			denyBtn.click();
			prodCount= driver.findElementsById("com.husqvarna.connect.qa:id/tool_name_txt");
			for(int i=0;i<prodCount.size();i++)
			{	
				productName.add(prodCount.get(i).getText()); 
			}
			scroll("325iLK");
			//			driver.findElements(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).setMaxSearchSwipes(5).scrollIntoView(new UiSelector().text(\"325iLK\"))"));
			prodCount = driver.findElementsById("com.husqvarna.connect.qa:id/tool_name_txt");
			for(int i=0;i<prodCount.size();i++)
			{	
				productName.add(prodCount.get(i).getText()); 
			}
			System.out.println("Number of total Products : "+productName.size()+"\n");
			
			System.out.println("List of Products");
			System.out.println(productName+"\n");
			//			WebElement product325iLK = driver.findElementByXPath("//android.widget.TextView[@text='325iLK']");
			//			product325iLK.click();
			WebElement menu = driver.findElementByXPath("//android.widget.ImageView[@content-desc='More options']");
			menu.click();
			
			System.out.println("Menu Page : ");
			System.out.println("List of Menu : ");
			List<WebElement> menuList = driver.findElementsById("com.husqvarna.connect.qa:id/title");
			for(int i=0;i<menuList.size();i++)
			{	
				System.out.println(menuList.get(i).getText());
			}
			driver.findElementByXPath("//android.widget.TextView[@text='Customer support']").click();
			
			System.out.println("\nCustomer support Page : ");
			WebElement custMenuTittleBar = driver.findElementById("com.husqvarna.connect.qa:id/toolbar_title");
			display(custMenuTittleBar);
			WebElement supportTopic = driver.findElementById("com.husqvarna.connect.qa:id/customer_support_generic_topic_layout");
			display(supportTopic);
			supportTopic.click();
			WebElement supportTopic1 = driver.findElementByXPath("//android.widget.TextView[@text='Add/remove products']");
			supportTopic1.click();
			WebElement backBtn = driver.findElementByXPath("//android.widget.ImageButton[@content-desc='Navigate up']");
			//			backBtn.click();
			WebElement selectProduct = driver.findElementById("com.husqvarna.connect.qa:id/customer_support_product_layout");
			display(selectProduct);
			selectProduct.click();
			scroll("115iHD45");
			WebElement productLast = driver.findElementByXPath("//android.widget.TextView[@text='115iHD45']");
			display(productLast);
			productLast.click();
			backBtn.click();
			//			WebElement backBtn = driver.findElementByXPath("//android.widget.ImageButton[@content-desc='Navigate up']");
			WebElement comment = driver.findElementById("com.husqvarna.connect.qa:id/customer_support_query_edit_box");
			//			display(comment);
			comment.sendKeys("Test");
			driver.hideKeyboard();
			WebElement sendBtn = driver.findElementById("com.husqvarna.connect.qa:id/customer_support_send_btn");
			display(sendBtn);
			sendBtn.click();
			List<WebElement> emailSendPage = driver.findElementsByXPath("//*[@text='CLOSE']/preceding-sibling::android.widget.TextView");
			
			System.out.println("\nCustomer support Email Page : ");
			for(int i=0;i<emailSendPage.size();i++)
			{	
				System.out.println(emailSendPage.get(i).getText());
			}
			WebElement closeBtn = driver.findElementByXPath("//*[@text='CLOSE']");
			display(closeBtn);

		}

	}
	public static void scroll( String scrollOption)
	{
		driver.findElements(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).setMaxSearchSwipes(5).scrollIntoView(new UiSelector().text(\""+scrollOption+"\"))"));
	}
	public static void main( String[] args ) throws MalformedURLException
	{
		try 
		{	
			launch("com.husqvarna.connect.qa", "com.husqvarna.connect.features.SplashScreenActivity");
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}
}
