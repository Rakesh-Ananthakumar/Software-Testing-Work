package com.codingcontest;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class AppTest 
{
    public WebDriver driver;
    public XSSFWorkbook workbook;
    public ExtentReports extentReports;
    public String toSearch;
    public Actions action;
    public JavascriptExecutor js;
    public ExtentTest test1,test2,test3;
    @BeforeTest
    public void setup() throws IOException
    {
        
        driver = new ChromeDriver();
        js = (JavascriptExecutor)driver;
        extentReports = new ExtentReports();
        
        workbook = new XSSFWorkbook("C:\\Users\\rakes\\Desktop\\cc2\\src\\Excel\\InputData.xlsx");
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        toSearch = sheet.getRow(1).getCell(0).getStringCellValue();

        action = new Actions(driver);
        
        ExtentSparkReporter spark = new ExtentSparkReporter("C:\\Users\\rakes\\Desktop\\cc2\\src\\report\\report.html");
        extentReports.attachReporter(spark);
        spark.config().setDocumentTitle("Report Title");
        spark.config().setTheme(Theme.DARK);

    }
    @Test
    public void testcase1() throws InterruptedException
    {
        
        driver.get("https://www.barnesandnoble.com");
        Thread.sleep(3000);

        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/div[1]/a")).click();
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/div[1]/div/a[2]")).click();
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/div[2]/div/input[1]")).sendKeys(toSearch);
        Thread.sleep(2000);
        
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/span/button")).click();
        Thread.sleep(2000);


        String searchResult = driver.findElement(By.xpath("//*[@id='searchGrid']/div/section[1]/section[1]/div/div[1]/div[1]/h1/span")).getText();

        
        test1 = extentReports.createTest("searchResult");
        if(searchResult.equals(toSearch))
        {
            test1.log(Status.PASS, "Search Results contain the keyword Chetan Bhagat");
        }
        else
        {
            test1.log(Status.FAIL, "Search Results does not contain the keyword Chetan Bhagat");

        }
        Thread.sleep(3000);

    }
    @Test
    public void testCase2() throws InterruptedException
    {
        
        driver.get("https://www.barnesandnoble.com");
        Thread.sleep(3000);
        
        
        action.moveToElement(driver.findElement(By.xpath("//*[@id=\"rhfCategoryFlyout_Audiobooks\"]"))).perform();
        Thread.sleep(2000);
        
        
        driver.findElement(By.xpath("//*[@id='navbarSupportedContent']/div/ul/li[5]/div/div/div[1]/div/div[2]/div[1]/dd/a[1]")).click();
        Thread.sleep(2000);
        
        driver.findElement(By.xpath("//*[@id='onetrust-close-btn-container']/button")).click();
        Thread.sleep(2000);
        
        js.executeScript("window.scrollBy(0,5000)", "");
        Thread.sleep(4000);


        
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div/div[2]/div/div[2]/section[2]/ol/li[1]/div/div[2]/div[5]/div[2]/div/div/form/input[11]")).click();
        Thread.sleep(2000);

        test2 = extentReports.createTest("Add to cart Test");
        test2.log(Status.PASS, "The Item is  added to the cart");
        Thread.sleep(3000);

    }
    @Test
    public void testCase3() throws InterruptedException
    {
        
        driver.get("https://www.barnesandnoble.com");
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("//*[@id='onetrust-close-btn-container']/button")).click();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,1000)", "");
        Thread.sleep(4000);
        
        driver.findElement(By.xpath("/html/body/main/div[3]/div[3]/div/section/div/div/div/div/div/a[1]/div")).click();
        Thread.sleep(4000);

        js.executeScript("window.scrollBy(0,1700)", "");
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("//*[@id='rewards-modal-link']")).click();
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("/html/body/main/section/div[1]/div[2]/div/div/div[2]/div/div[73]/div/div[1]/a")).click();
        Thread.sleep(3000);
        
        String signInText = driver.findElement(By.xpath("/html/body/div[2]/div/h2")).getText();
        System.out.println(signInText);
        Thread.sleep(3000);

        
        test3 = extentReports.createTest("Sign In Or create an account dialog");
        if(signInText.contains("Sign in or Create an Account"))
        {
            test3.log(Status.PASS, "Sign In Or create an account dialog is appears");
        }
        else
        {
            test3.log(Status.FAIL, "Sign In Or create an account dialog is not appears");
        }


    }
    @AfterTest
    public void afterTest()
    {
        extentReports.flush();
        driver.quit();
    }
}
