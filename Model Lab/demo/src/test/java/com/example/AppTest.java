package com.example;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class AppTest 
{
    WebDriver driver;
    XSSFWorkbook workbook;
    String amt;
    ExtentReports reports;
    ExtentTest test;

    @BeforeTest
    public void before()
    {
        driver = new ChromeDriver();
        ExtentSparkReporter exsprk = new ExtentSparkReporter("C:\\Users\\rakes\\Desktop\\Model Lab\\demo\\src\\report\\reports.html");
        reports = new ExtentReports();
        reports.attachReporter(exsprk);
    }

    @Test
    public void test1() throws Exception{
        
        test = reports.createTest("Test 1", "Groww website calculator");

        FileInputStream fs = new FileInputStream("C:\\Users\\rakes\\Desktop\\Testing lab\\Model\\groww\\gr.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);
        String loamnt = row.getCell(0).getStringCellValue();
        String rtint = row.getCell(1).getStringCellValue();
        String lotnt = row.getCell(2).getStringCellValue();

        driver.manage().window().maximize();
        driver.get("https://groww.in/");

        JavascriptExecutor js = (JavascriptExecutor)driver;

        driver.findElement(By.xpath("//*[@id='footer']/div/div[1]/div/div[1]/div[2]/div[3]/a[2]")).click();
        Thread.sleep(3000);

        js.executeScript("window.scrollBy(0,4500)", "null");
        driver.findElement(By.xpath("//*[@id='root']/div[2]/div[2]/a[15]/div/p[1]")).click();
        Thread.sleep(3000);

        WebElement element =  driver.findElement(By.xpath("//*[@id='LOAN_AMOUNT']"));
        element.clear();
        element.sendKeys(loamnt);

        element = driver.findElement(By.xpath("//*[@id='RATE_OF_INTEREST']"));
        element.clear();
        element.sendKeys(rtint);

        element = driver.findElement(By.xpath("//*[@id='LOAN_TENURE']"));
        element.clear();
        element.sendKeys(lotnt);

        String res = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div/p")).getText();
        Assert.assertTrue(res.contains("Details"));
    }
    @AfterMethod
    public void afterTest(ITestResult result) throws Exception{
        if(result.getStatus()==ITestResult.FAILURE)
        {
            test.log(Status.FAIL, "Test Case Failed: "+result.getName());
            test.log(Status.FAIL, "Test Case Failed Reason: "+result.getThrowable());
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String path = "C:\\Users\\rakes\\Desktop\\Model Lab\\demo\\ss.jpg";
            FileUtils.copyFile(screenshot,new File(path));
            test.addScreenCaptureFromPath(path);

        }
        else if (result.getStatus()==ITestResult.SUCCESS)
        { test.log(Status.PASS, "Test Case Passed: "+result.getName());
        }
      else if (result.getStatus()==ITestResult.SKIP)
        { test.log(Status.SKIP, "Test Case Skipped: "+result.getName());
        }
    }
    @AfterTest
    public void close()
    {
        reports.flush();
        driver.quit();
    }
}
