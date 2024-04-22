package com.example.day6que1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day6que1Application {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();

		driver.get("https://economictimes.indiatimes.com/et-now/results");

		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='topnav']/div[10]/a")).click();
		Thread.sleep(2000);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,300)", "");
		Thread.sleep(2000);
		
		
		Select obj1 = new Select(driver.findElement(By.id("amcSelection")));
		obj1.selectByVisibleText("Canara Robeco");
		Thread.sleep(2000);
		Select obj2 = new Select(driver.findElement(By.name("schemenm")));
		// obj2.selectByVisibleText("Canara Robeco Bluechip Equity Direct-G");
		obj2.selectByIndex(5);
		
		Thread.sleep(2000);

		driver.findElement(By.id("getDetails")).click();
		Thread.sleep(3000);
		
		

		driver.findElement(By.xpath("//*[@id='installment_amt']/li/span")).click();;
		driver.findElement(By.xpath("//*[@id='installment_amt']/li/ul/li[3]/span")).click();
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='installment_period']/li/span")).click();;
		driver.findElement(By.xpath("//*[@id='installment_period']/li/ul/li[4]/span")).click();

		
		Thread.sleep(3000);


		
		











		//Demo To access iframe
		// driver.get("https://demo.automationtesting.in/Frames.html");
		// WebElement frame= driver.findElement(By.tagName("iframe"));
		// driver.switchTo().frame(frame);
		// driver.findElement(By.xpath("/html/body/section/div/div/div/input")).sendKeys("Hi Bro");
		SpringApplication.run(Day6que1Application.class, args);
	}

}
