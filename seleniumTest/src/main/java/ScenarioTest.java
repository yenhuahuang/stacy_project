import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import ScreenshotsUtils.TakeScreenshots;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ScenarioTest {

    private String USERNAME;
    private String PASSWORD;
    public String baseUrl = "https://my.northeastern.edu/";
    String driverPath = "/Users/stacyhuang/chromedriver";
    ExtentTest logger;
    private ExtentReports report;
    public WebDriver driver ;
    ExtentHtmlReporter htmlReporter;
     
      @BeforeTest
      public void before() throws FileNotFoundException {
          System.setProperty("webdriver.chrome.driver", driverPath);
          driver = new ChromeDriver();
          driver.get(baseUrl);
          setVariables();
          report = new ExtentReports();

          htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/reports/TestReport.html");
          report.attachReporter(htmlReporter);
          report.setSystemInfo("Host Name", "Kshitiz Gupta");
          report.setSystemInfo("Environment", "Mac");
      }

      @Test
      public void addFavorite() throws Exception {

          //home page
          int ele_size = driver.findElements(By.cssSelector("a.btn.btn-primary.normal")).size();
          driver.findElements(By.cssSelector("a.btn.btn-primary.normal")).get(ele_size - 1).click();
          Thread.sleep(3000);

          //enter username and password
          driver.findElement(By.id("username")).clear();
          driver.findElement(By.id("username")).sendKeys(USERNAME);
          driver.findElement(By.id("password")).clear();
          driver.findElement(By.id("password")).sendKeys(PASSWORD);

          TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario1-addFavorite/1_login.png");
          Thread.sleep(3000);

          //login button perform
          driver.findElement(By.cssSelector("button.form-element.form-button")).click();
          System.out.println("waiting for clicking service&link");
          TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario1-addFavorite/2_homepage.png");
          Thread.sleep(8000);

          //click service&links tab
          driver.findElement(By.xpath("//a[@href='https://my.northeastern.edu/group/student/services-links']")).click();
          TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario1-addFavorite/3_add_favorite.png");

          System.out.println("waiting for adding favorite");
          //this is main window
          String mainWindow=driver.getWindowHandle();
          Thread.sleep(3000);

          //if two links
          driver.findElement(By.xpath("//a[@href='http://northeastern.bncollege.com']")).click();
          TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario1-addFavorite/4_add_favorite.png");

          Thread.sleep(2000);
          System.out.println("click 1 window");

          driver.findElement(By.xpath("//a[@href='https://northeastern.secure.force.com/public/apex/ITSTutorEntry']")).click();
          TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario1-addFavorite/5.png");

          Thread.sleep(2000);
          System.out.println("click 2 window");

          //open and close child windows
          Set<String> s1=driver.getWindowHandles();
          Iterator<String> i1=s1.iterator();
          while(i1.hasNext()) {
              String childWindow=i1.next();
              if(!mainWindow.equalsIgnoreCase(childWindow)) {
                  driver.switchTo().window(childWindow);
                  System.out.println("in window");
                  driver.close();
              }
          }
          driver.switchTo().window(mainWindow);

          //add favorites(first and second)
          driver.findElement(By.xpath("(//i[@class='add-link icon icon-heart-empty'])[1]")).click();
          TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario1-addFavorite/6.png");

          Thread.sleep(3000);
          driver.findElement(By.xpath("(//i[@class='add-link icon icon-heart-empty'])[2]")).click();
          TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario1-addFavorite/7.png");

          Thread.sleep(3000);

      }

      @Test
      public void searchCourse() throws Exception {


            //go to student registration
            driver.findElement(By.xpath("//a[@href='https://nubanner.neu.edu/StudentRegistrationSsb']")).click();
            Thread.sleep(3000);
            TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario2-searchCourse/8_StudentRegistration.png");

            //go to the new window
            ArrayList<String> tabs = new ArrayList (driver.getWindowHandles());
            System.out.println(tabs.size());
            driver.switchTo().window(tabs.get(1));
            Thread.sleep(2000);

            //class search link
            driver.findElement(By.xpath("//*[@id='classSearchLink']")).click();
            Thread.sleep(2000);
            TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario2-searchCourse/9_classSearch.png");
            
            //course registration
            driver.findElement(By.xpath("//*[@id='s2id_txt_term']")).click();
            Thread.sleep(8000);
            TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario2-searchCourse/10_searchTerm.png");

            //select Summer Full 2020
            driver.findElement(By.xpath("//*[@id='202050']")).click();
            Thread.sleep(2000);
            TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario2-searchCourse/11_searchSubject.png");

            //click continue
            driver.findElement(By.xpath("//*[@id='term-go']")).click();
            Thread.sleep(2000);
            TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario2-searchCourse/12_term.png");
            
            //click subject box
            driver.findElement(By.cssSelector("#s2id_autogen1")).click();
            Thread.sleep(2000);
            TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario2-searchCourse/13.png");

            //choose Accounting
            driver.findElement(By.xpath("//*[@id='ACCT']")).click();
            Thread.sleep(2000);
            TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario2-searchCourse/14_choosesubject.png");

            //click search
            driver.findElement(By.xpath("//*[@id='search-go']")).click();
            Thread.sleep(3000);
            TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario2-searchCourse/15_result.png");

        }

    @Test
    public void createPlan() throws Exception {
        logger = report.createTest("Create Plan");

        driver.get("https://my.northeastern.edu/c/portal/login");
        Thread.sleep(2000);

        TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario3-createPlan/16.png");
        
        Thread.sleep(15000);
        //go to service-link
        logger = report.createTest("course browse");
        driver.findElement(By.xpath("//nav[@id='navigation']")).click();
        driver.findElement(By.xpath("//a[@href='https://my.northeastern.edu/group/student/services-links']")).click();
        TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario3-createPlan/17.png");
        Thread.sleep(2000);
        
        //click course registration (NEW)
        driver.findElement(By.xpath("//a[contains(text(),' Course Registration (NEW) ')]")).click();
        TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario3-createPlan/18.png");
        Thread.sleep(2000);
        
        //go to new window
        String winHandleBefore = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario3-createPlan/18-1.png");
        
        //select drop down
        driver.findElement(By.xpath("//a[@id='planningLink']")).click();
        TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario3-createPlan/19.png");
        Thread.sleep(2000);
        
        //select drop down menu
        driver.findElement(By.xpath("//a[@href='javascript:void(0)']")).click();
        TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario3-createPlan/20.png");
        Thread.sleep(2000);
        
        //click fall 2020
        driver.findElement(By.xpath("//div[@id='202110']")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
        TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario3-createPlan/21.png");
        Thread.sleep(2000);
        
        //click create plan
        driver.findElement(By.xpath("//button[@id='createPlan']")).click();
        TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario3-createPlan/22.png");
        Thread.sleep(2000);
        
        //input into subject box
        driver.findElement(By.xpath("//input[@id='s2id_autogen1']")).sendKeys("Information Systems Program");
        TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario3-createPlan/23.png");
        Thread.sleep(2000);
        
        //show result
        driver.findElement(By.xpath("//div[@id='INFO']")).click();
        TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario3-createPlan/23-1.png");
        
        driver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();
        TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario3-createPlan/24.png");
        Thread.sleep(5000);

        //select courses
        for (int i = 0; i < 3; i++) {
            driver.findElements(By.xpath("//button[contains(text(),'Add Course')]")).get(i).click();
            Thread.sleep(1000);
            TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario3-createPlan/24-"+i+".png");
        }
        
        
        //save new plan name
        driver.findElement(By.xpath("//button[contains(text(),'Save Plan')]")).click();
        TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario3-createPlan/25.png");
        
        driver.findElement(By.xpath("//input[@id='txt_planDesc']")).sendKeys("selenium");
        TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario3-createPlan/25-1.png");
        Thread.sleep(1000);
        
        //click to save plan
        driver.findElement(By.xpath("//button/span[contains(text(),'Save')]")).click();
        TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario3-createPlan/26.png");
        Thread.sleep(3000);
        
        //to select the created plan 
        driver.findElement(By.xpath("//a[contains(text(),'Select A Plan')]")).click();
        TakeScreenshots.takeSnapShot(driver,"./screenshots/scenario3-createPlan/27.png");
        Thread.sleep(5000);
        
        driver.close();
        
        driver.switchTo().window(winHandleBefore);
        Thread.sleep(3000);

        messagePrinter("plan created successful", "plan created successful", logger);
        logger.pass("Create Plan Test Scenario : Positive case successfull");
    }

    @AfterTest
      public void after(){
          driver.quit();
      }

    private void setVariables() throws FileNotFoundException {
        File vars = new File("./vars");
        Scanner scnr = new Scanner(vars);
        this.USERNAME = scnr.nextLine();
        this.PASSWORD = scnr.nextLine();
    }

    public static void messagePrinter(String actual, String expected, ExtentTest logger) {
        try {
            Assert.assertEquals(actual, expected);
            logger.pass("The actual val:  " + actual);
            logger.pass("The expected val:" + expected);
            logger.pass("------------------------------------------");
        } catch (Error e) {
            logger.fail("The actual val:  " + actual);
            logger.fail("The expected val:" + expected);
            logger.fail(e.toString());
            logger.fail("------------------------------------------");
        }
    }
}