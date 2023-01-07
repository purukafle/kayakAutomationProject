package com.libraries;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GlobalSelenium {

	private WebDriver driver;
	private static int waitTimeInSecs = 30;
	
	
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public enum Browser {
		CHROME, FIREFOX, SAFARI, EDGE_CHROMIUM
	}

	public WebDriver startABrowser(Browser browser) {
		try {
			switch (browser) {

			case CHROME:
				driver = startChromeBrowser();
				break;

			case FIREFOX:
				driver = startFirefoxBrowser();
				break;

			case SAFARI:
				driver = startSafariBrowser();
				break;

			case EDGE_CHROMIUM:
				driver = startEdgeBrowser();
				break;

			default:
				System.out.println("Currently framework do not support this type of browser!");
				System.out.println("Default browser set to 'CHROME' ");
				driver = startChromeBrowser();
				break;
			}
			driver.manage().deleteAllCookies();
		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(true, false);
		}
		return driver;
	}

	private WebDriver startEdgeBrowser() {
		try {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			pageSync();
		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(true, false);
		}

		return driver;
	}

	private WebDriver startSafariBrowser() {
		// This is homework, please try to implement this method after today's class

		return driver;
	}

	private WebDriver startFirefoxBrowser() {
		try {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			pageSync();
		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(true, false);
		}

		return driver;
	}

	private WebDriver startChromeBrowser() {
		try {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			pageSync();
		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(true, false);
		}
		return driver;
	}

	private void pageSync() {
		customWait(5);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTimeInSecs));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(waitTimeInSecs));
	}

	public void tearDown() {
		try {
			customWait(5);
			// close the browser
			driver.close();
			driver.quit();

		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(true, false);
		}
	}

	public void highlightWebElement(WebElement element) {
		try {
			WrapsDriver wrappedElement = (WrapsDriver) element;
			JavascriptExecutor js = (JavascriptExecutor) wrappedElement.getWrappedDriver();

			for (int i = 1; i < 4; i++) {
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
						"color: red; border: 2px solid yellow");
				customWait(0.5);
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
				customWait(0.5);
			}
			customWait(3);
		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(true, false);
		}
	}

	public void handleCheckBox(By by, boolean isChecked) {
		try {
			WebElement checkBox = driver.findElement(by);
			// User wants to check the box
			if (isChecked == true) {

				if (checkBox.isSelected()) {
					// box is checked----------------> nothing
				} else {
					// box is empty(Not checked)-----> Click
					checkBox.click();
				}
			} else {
				// User wants to un-check the box
				if (checkBox.isSelected()) {
					// box is checked----------------> Click
					checkBox.click();
				} else {
					// box is empty(Not checked)-----> nothing
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(true, false);
		}
	}

	public String takeScreenshot(String screenshotName) {
		String finalScreenshotPath = null;

		try {
			String fileLocation = "target/" + screenshotName + "_" + getCurrentTime() + ".png";
			File absFilePath = new File(fileLocation);
			String newPath = absFilePath.getAbsolutePath();

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(scrFile, new File(newPath));
			finalScreenshotPath = newPath;
			System.out.println("screenshot location ----> " + newPath);

		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(true, false);
		}

		return finalScreenshotPath;
	}

	public void enterText(By by, String inputString) {
		try {
			WebElement element = driver.findElement(by);
			element.clear();
			element.sendKeys(inputString);

		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(true, false);
		}
	}
	
	public void enterText(By by, Keys keys) {
		try {
			WebElement element = driver.findElement(by);
			element.clear();
			element.sendKeys(keys);

		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(true, false);
		}
		
	}

	public void selectDropDown(By by, String visibileOptionText) {
		try {
			WebElement dropDownElem = driver.findElement(by);
			Select select = new Select(dropDownElem);
			select.selectByVisibleText(visibileOptionText);

		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(true, false);
		}
	}

	public void clickElement(By by) {

		try {
			WebElement element = driver.findElement(by);
			element.click();

		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(true, false);
		}
	}

	public void hoverOver(By mainMenuBy, By subMenuBy) {

		try {
			WebElement mainMenuElem = driver.findElement(mainMenuBy);

			// Moving mouse to Main menu elem
			Actions action = new Actions(driver);
			action.moveToElement(mainMenuElem).build().perform();

			// delay or pause 0.5 second for sub-menu to display/open
			customWait(0.5);

			WebElement subMenuElem = driver.findElement(subMenuBy);
			// action.moveToElement(trackPackElem).click().build().perform();

			action.moveToElement(subMenuElem).build().perform();
			subMenuElem.click();
		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(true, false);
		}
	}

	
	
	
	
	
///////// Helper Methods ---------------
//if within the same class, helper method should be declared in private
//

	public void customWait(double inSeconds) {
		try {
			// casting / converting data type from Double to Long
			long seconds = (long) (inSeconds * 1000);

			Thread.sleep(seconds);

			// below line of code is same 2 steps above merging into one line
			// Thread.sleep((long) (inSeconds * 1000));

		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(true, false);
		}
	}

	public String getCurrentTime() {
		String finalTimeStamp = null;
		try {
			Date date = new Date();
			// System.out.println("current date1: " + date);
			String tempTime = new Timestamp(date.getTime()).toString();
			// System.out.println("current date2: " + tempTime);
			String newTemp = tempTime.replace(':', '_').replace('.', '_').replace(' ', '_').replace('-', '_');
			// System.out.println("current date3: " + newTemp);
			String newTemp2 = newTemp.replaceAll("_", "");
			// System.out.println("current date4: " + newTemp2);
			finalTimeStamp = newTemp2;

		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(true, false);
		}
		return finalTimeStamp;
	}

	public static void main(String[] args) {
		GlobalSelenium myLibrary = new GlobalSelenium();
		myLibrary.getCurrentTime();

	}

}