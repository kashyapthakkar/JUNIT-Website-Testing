package testproject_selenium;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * COMP10066 - Assignment#6 starter code
 *
 * @author mark.yendt@mohawkcollege.ca, Kashyap Thakkar, 000742712
 */
public class TestProject_SeleniumTest {

    enum Browser {
        FIREFOX, CHROME, IE
    };

    Browser browser = Browser.IE;

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    private WebDriver startBrowser(Browser browser) throws Exception {
        WebDriver tempDriver = null;
        // Tested at College on Nov 19,2018
        if (browser == Browser.CHROME) {
            System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
            tempDriver = new ChromeDriver();
        } else if (browser == Browser.FIREFOX) {
            System.setProperty("webdriver.gecko.driver", "resources\\geckodriver.exe");
            // May need to do this if not in path
            // String pathToBinary = "C:\\Program Files\\Mozilla Firefox 61-32-bit\\firefox.exe";
            // System.setProperty("webdriver.firefox.driver",pathToBinary);
            // FirefoxProfile firefoxProfile = new FirefoxProfile();
            tempDriver = new FirefoxDriver();
        } else if (browser == Browser.IE) {
            System.setProperty("webdriver.ie.driver", "resources\\IEDriverServer.exe");
            tempDriver = new InternetExplorerDriver();
        }
        return tempDriver;
    }

    private void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }


    /*
     Example Test case - driver is assummed to be set-up
     Your other test cases need to resemble these 
     Obtain more test cases by recording scripts using Katalon and exporting to
     Java - Junit 4 Web-Driver format
     */
    public void testLoginAdmin() throws Exception {

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://csunix.mohawkcollege.ca/tooltime/comp10066/A3/login.php");
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("adminP6ss");
        driver.findElement(By.name("Submit")).click();
        driver.findElement(By.linkText("User Admin")).click();
        driver.findElement(By.linkText("Logout")).click();
        driver.findElement(By.id("loginname")).click();
        try {
            assertEquals("Not Logged In", driver.findElement(By.id("loginname")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
   
    
    
   /**
    * test method to create a user and check that can user login.
    */
  public void testCreate() throws Exception {
    driver.get("https://csunix.mohawkcollege.ca/tooltime/comp10066/A3/login.php");
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("adminP6ss");
    driver.findElement(By.name("Submit")).click();
    driver.findElement(By.linkText("User Admin")).click();
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("kashyap1111");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("Kashyap@1111");
    driver.findElement(By.name("activate")).click();
    driver.findElement(By.name("admin")).click();
    driver.findElement(By.name("Add New Member")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Members Administration'])[1]/following::div[1]")).click();
    try {
      assertEquals("Record successfully inserted.", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Members Administration'])[1]/following::div[1]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.linkText("Logout")).click();
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("kashyap1111");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("Kashyap@1111");
    driver.findElement(By.name("Submit")).click();
    try {
      assertEquals("User: kashyap1111", driver.findElement(By.id("loginname")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.linkText("Logout")).click();
  }
  
    
  
  /**
    * test method to delete a user.
    */
  public void testDelete() throws Exception {
    driver.get("https://csunix.mohawkcollege.ca/tooltime/comp10066/A3/login.php");
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("adminP6ss");
    driver.findElement(By.name("searchByPC")).submit();
    driver.findElement(By.id("loginname")).click();
    try {
      assertEquals("User: admin", driver.findElement(By.id("loginname")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.linkText("User Admin")).click();
    driver.get("https://csunix.mohawkcollege.ca/tooltime/comp10066/A3/adminuser.php?act=delete&username=kashyap1111");
    driver.findElement(By.linkText("here")).click();
    try {
      assertEquals("User kashyap1111 was successfully deleted.", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Members Administration'])[1]/following::div[1]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.linkText("Logout")).click();
  }
    
  
  
  /**
    * test method to check that directories are showing exact amount of data as shown in city {x}
    */
  public void testCityDirectory(String city) throws Exception {
    driver.get("https://csunix.mohawkcollege.ca/tooltime/comp10066/A3/login.php");
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("adminP6ss");
        driver.findElement(By.name("Submit")).click();
        driver.findElement(By.linkText("Directory")).click();
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("body")).click();
        driver.findElement(By.id("city")).click();
        new Select(driver.findElement(By.id("city"))).selectByVisibleText(city);
        driver.findElement(By.id("city")).click();
        driver.findElement(By.name("submit")).click();
    try {
            boolean result = false;
            String[] stringArray = city.split(" ");                             //split city name and number of data
            String numbers = stringArray[1];                                    //number of data
            numbers = numbers.replace("{" , "");                                //remove "{"
            numbers = numbers.replace("}" , "");                                //remove "}"
            int number = Integer.parseInt(numbers);                             //convert string to integer
            
            //check that data is equals to number of data
            for (int i = 1; i <= number; ++i) 
            {
                String path = "//ul[@class='companylist'][@id='company_" + i + "']//li[3]";
                WebElement element = driver.findElement(By.xpath(path));
                String response = element.getText();
                result = response.contains(stringArray[0]);
            }
            assertEquals(result, true);
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    driver.findElement(By.linkText("Logout")).click();
  }

  
  
    /**
     * This code will run all of the test cases - DO NOT CHANGE except to add
     * more test cases as shown below:
     */
    @Test
    public void testRunner() {
        // Will run through asll enum cases 
        for (Browser browser : Browser.values()) {
            try {

                driver = startBrowser(browser);

                //----------------------------------------
                // PUT YOUR TEST CASES AFTER this line 
                testLoginAdmin();                                               //test for login admin
                testCreate();                                                   //test to create a user
                testDelete();                                                   //test to delete a user
                testCityDirectory("Ajax {1}");                                  //test to check that Ajax city has 1 data
                testCityDirectory("Bancroft {2}");                              //test to check that Bancroft city has 2 data
                testCityDirectory("Brantford {7}");                             //test to check that Brantford city has 7 data
                testCityDirectory("Dartmouth {3}");                             //test to check that Dartmouth city has 3 data

                // DO NOT MODIFY below line
                // ----------------------------------------
                closeBrowser();
                
            } catch (Exception ex) {
                System.err.println("Exception caught starting " + browser + " " + ex.getMessage());
            }

        }
    }
}
