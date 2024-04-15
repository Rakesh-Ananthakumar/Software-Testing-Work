package com.example.day5pah1;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class Day5pah1Application {

	public static void main(String[] args) throws InterruptedException, IOException{

		WebDriver driver = new ChromeDriver();
		driver.get("https://magento.softwaretestingboard.com/");
		Thread.sleep(2000);

		driver.findElement(By.id("search")).sendKeys("Shoes");
		Thread.sleep(5000);
		if(driver.findElement(By.xpath("//*[@id='maincontent']/div[1]/h1/span")).getText().contains("Shoes"))
		{
			System.out.println("Search for results : Shoes is present");
		}
		else
		{
			System.out.println("Search for results : Shoes is not present");
		}
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File file = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("./screenshots/shoe-evidence.png"));
		Thread.sleep(3000);


		driver.findElement(By.xpath("//*[@id='search_mini_form']/div[2]/button")).click();
		Thread.sleep(2000);
		
		Actions actions = new Actions(driver);
		
		WebElement element1 = driver.findElement(By.xpath("//*[@id='ui-id-5']/span[2]"));
        actions.moveToElement(element1).perform();
		Thread.sleep(1000);
		
		WebElement element = driver.findElement(By.xpath("//*[@id='ui-id-17']/span[2]"));
        actions.moveToElement(element).perform();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//*[@id='ui-id-20']/span")).click();
		Thread.sleep(3000);
		
		driver.navigate().to("https://magento.softwaretestingboard.com/");
		Thread.sleep(2000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,650)", "");	
		
		driver.findElement(By.xpath("//*[@id='maincontent']/div[3]/div/div[3]/div[1]/div/a[2]/span[1]/img")).click();
		Thread.sleep(2000);
		
		driver.findElement((By.xpath("//*[@id='maincontent']/div[3]/div[1]/div[4]/ol/li[3]/div/a/span/span/img"))).click();
		Thread.sleep(2000);
		
		driver.findElement((By.xpath("//*[@id='option-label-size-143-item-168']"))).click();
		Thread.sleep(2000);

		driver.findElement((By.xpath("//*[@id='option-label-color-93-item-56']"))).click();
		Thread.sleep(2000);

		driver.findElement(By.id("qty")).clear();
		driver.findElement(By.id("qty")).sendKeys("4");
		driver.findElement(By.xpath("//*[@id='product-addtocart-button']")).click();
		Thread.sleep(5000);
		driver.close();

		
	}		
}
