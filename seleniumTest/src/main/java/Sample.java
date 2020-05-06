import ScreenshotsUtils.TakeScreenshots;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Sample {

    private String USERNAME;
    private String PASSWORD;

    @Test
    public void hello() throws InterruptedException, FileNotFoundException {

        setVariables();

        System.setProperty("webdriver.chrome.driver","/Users/gaomc/chromedriver");
        WebDriver driver  = new ChromeDriver();
        driver.get("https://my.northeastern.edu/");
        System.out.println(driver.getWindowHandle());

        int ele_size = driver.findElements(By.cssSelector("a.btn.btn-primary.normal")).size();
        driver.findElements(By.cssSelector("a.btn.btn-primary.normal")).get(ele_size - 1).click();
        try {
            TakeScreenshots.takeSnapShot(driver, "./screenshots/1.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread.sleep(2000);

        driver.findElement(By.id("username")).sendKeys(USERNAME);
        driver.findElement(By.id("password")).sendKeys(PASSWORD);

        driver.findElement(By.cssSelector("button.form-element.form-button")).click();
        Thread.sleep(10000);

        driver.findElement(By.xpath("//a[@href='https://my.northeastern.edu/group/student/services-links']")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[@href='https://nubanner.neu.edu/StudentRegistrationSsb']")).click();
        Thread.sleep(3000);


        ArrayList<String> tabs = new ArrayList (driver.getWindowHandles());
        System.out.println(tabs.size());
        driver.switchTo().window(tabs.get(1));


        driver.findElement(By.xpath("//*[@id='classSearchLink']")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id='s2id_txt_term']")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id='202050']")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id='term-go']")).click();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("#s2id_autogen1")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id='ACCT']")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id='search-go']")).click();

        Thread.sleep(80000);

        driver.quit();
    }

    private void setVariables() throws FileNotFoundException {
        File vars = new File("./vars");
        Scanner scnr = new Scanner(vars);
        this.USERNAME = scnr.nextLine();
        this.PASSWORD = scnr.nextLine();
    }

}
