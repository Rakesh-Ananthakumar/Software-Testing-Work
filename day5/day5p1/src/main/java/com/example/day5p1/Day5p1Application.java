package com.example.day5p1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

@SpringBootApplication
public class Day5p1Application {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://demoblaze.com/");
		driver.findElement(By.linkText("Laptops")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("MacBook air")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Add to cart")).click();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Cart")).click();
		Thread.sleep(3000);
		List<WebElement> list = driver.findElements(By.tagName("td"));

		System.out.println(list.size());
		System.out.println("Tilte : "+list.get(1).getText());
		System.out.println("Price : "+list.get(2).getText());

		SpringApplication.run(Day5p1Application.class, args);
	}

}
