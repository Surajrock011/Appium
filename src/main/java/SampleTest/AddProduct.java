package SampleTest;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AddProduct 
{	
	public static AndroidDriver driver;
	public static WebDriverWait wait;
	public static Set<String> productName = new HashSet<String>();;
	public static List<WebElement> prodCount;


	public void setup()
	{
		try
		{
			DesiredCapabilities cap=new DesiredCapabilities();
			cap.setCapability("platformName", "Android");
			cap.setCapability("platformVersion", "10");
			cap.setCapability("deviceName", "emulator-5554");
			cap.setCapability("appPackage", "com.husqvarna.connect.qa");
			cap.setCapability("appActivity","com.husqvarna.connect.features.SplashScreenActivity");
			driver= new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),cap);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

	}

	@BeforeTest
	public void login()
	{	
		WebElement loginBtn = driver.findElementById("com.husqvarna.connect.qa:id/landing_login_btn");
		loginBtn.click();
		WebElement emailId = driver.findElementById("com.husqvarna.connect.qa:id/login_email_edit_text");
		emailId.sendKeys("surajrock011@gmail.com");
		WebElement passward = driver.findElementById("com.husqvarna.connect.qa:id/login_password_edit_text");
		passward.sendKeys("123456");
		WebElement loginBtn1 = driver.findElementById("com.husqvarna.connect.qa:id/login_btn");
		loginBtn1.click();
		WebElement allowdBtn = driver.findElementById("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
		allowdBtn.click();
		driver.findElementByXPath("//android.widget.Button[@text='ALLOW']").click();
	}

	@Test(priority=1)
	public void verifyCountryName()
	{	
		WebElement menu = driver.findElementByXPath("//android.widget.ImageView[@content-desc='More options']");
		menu.click();
		driver.findElementByXPath("//android.widget.TextView[@text='Settings']").click();
		WebElement country =  driver.findElementById("com.husqvarna.connect.qa:id/setting_country_name");
		if(country.getText().contains("United States"))
		{
			System.out.println("The selected country name is "+country.getText());

		}
		else
		{
			country.click();
			driver.findElements(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).setMaxSearchSwipes(5).scrollIntoView(new UiSelector().text(\"United States\"))"));
			driver.findElementByXPath("//android.widget.TextView[@text='United States']").click();
			WebElement backBtn = driver.findElementByXPath("//android.widget.ImageButton[@content-desc='Navigate up']");
			backBtn.click();
			System.out.println("The selected country name is "+country.getText());
		}
		driver.findElementByXPath("//android.widget.ImageButton[@content-desc='Navigate up']").click();
	}
	@Test(priority=2)
	public void addProduct1()
	{	
		driver.findElementById("com.husqvarna.connect.qa:id/home_fab").click();
		driver.findElementByXPath("//android.widget.TextView[@text='MANUAL INPUT']").click();
		driver.findElementByXPath("//android.widget.TextView[@text='Chainsaws']").click();
		driver.findElementByXPath("//android.widget.TextView[@text='120i']").click();
		driver.findElementById("com.husqvarna.connect.qa:id/middle_serial_chooseProduct").click();
		driver.findElementByXPath("//android.widget.TextView[@text='967098102']").click();
		driver.findElementById("com.husqvarna.connect.qa:id/dialog_positive").click();
		driver.findElementById("com.husqvarna.connect.qa:id/middle_serial_enterSerialNumber_editText").sendKeys("20183700068");
		driver.hideKeyboard();
		driver.findElementById("com.husqvarna.connect.qa:id/middle_serial_continue_button").click();
		driver.findElementById("com.husqvarna.connect.qa:id/found_prod_add_button").click();
		System.out.println("Product 120i is added");

	}


	@Test(priority=3)
	public void addProduct2()
	{

		driver.findElementById("com.husqvarna.connect.qa:id/home_fab").click();
		driver.findElementByXPath("//android.widget.TextView[@text='MANUAL INPUT']").click();
		driver.findElementByXPath("//android.widget.TextView[@text='Chainsaws']").click();
		driver.findElementByXPath("//android.widget.TextView[@text='330i']").click();
		WebElement hqID=driver.findElementById("com.husqvarna.connect.qa:id/middle_hid_enterHid_editText");
		hqID.sendKeys("9678937003732019025011205");
		driver.hideKeyboard();
		driver.findElementById("com.husqvarna.connect.qa:id/middle_hid_continue_button").click();
		driver.findElementById("com.husqvarna.connect.qa:id/found_prod_add_button").click();

		System.out.println("Product 330i is added");


	}
	@Test(priority=4)
	public void addProduct3()
	{	

		driver.findElementById("com.husqvarna.connect.qa:id/home_fab").click();
		driver.findElementByXPath("//android.widget.TextView[@text='MANUAL INPUT']").click();
		driver.findElements(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).setMaxSearchSwipes(5).scrollIntoView(new UiSelector().text(\"Hedge trimmers\"))"));
		driver.findElementByXPath("//android.widget.TextView[@text='Hedge trimmers']").click();
		driver.findElementByXPath("//android.widget.TextView[@text='115iHD55']").click();
		driver.findElementById("com.husqvarna.connect.qa:id/middle_serial_chooseProduct").click();
		driver.findElementByXPath("//android.widget.TextView[@text='967098604']").click();
		driver.findElementById("com.husqvarna.connect.qa:id/dialog_positive").click();
		driver.findElementById("com.husqvarna.connect.qa:id/middle_serial_enterSerialNumber_editText").sendKeys("20191300348");
		driver.hideKeyboard();
		driver.findElementById("com.husqvarna.connect.qa:id/middle_serial_continue_button").click();
		driver.findElementById("com.husqvarna.connect.qa:id/found_prod_add_button").click();
		System.out.println("Product 115iHD55 is added");
	}
	@AfterTest
	public void showProducts()
	{
		prodCount= driver.findElementsById("com.husqvarna.connect.qa:id/tool_name_txt");
		for(int i=0;i<prodCount.size();i++)
		{	
			productName.add(prodCount.get(i).getText()); 
		}
		driver.findElements(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).setMaxSearchSwipes(5).scrollIntoView(new UiSelector().text(\"325iLK\"))"));
		//			driver.findElements(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).setMaxSearchSwipes(5).scrollIntoView(new UiSelector().text(\"325iLK\"))"));
		prodCount = driver.findElementsById("com.husqvarna.connect.qa:id/tool_name_txt");
		for(int i=0;i<prodCount.size();i++)
		{	
			productName.add(prodCount.get(i).getText()); 
		}
		System.out.println("Number of total Products : "+productName.size()+"\n");

		System.out.println("List of Products");
		System.out.println(productName+"\n");
	}
	@AfterSuite
	public void close()
	{	
		System.out.println("Close Application");
		driver.closeApp();
	}

}

