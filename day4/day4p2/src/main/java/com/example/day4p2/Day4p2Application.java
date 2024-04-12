package com.example.day4p2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day4p2Application {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.shoppersstop.com/");
		driver.findElement(By.className("user-icon")).click();
		driver.close();

		SpringApplication.run(Day4p2Application.class, args);
	}

}
