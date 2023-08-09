package com.thetestingacademy.july22;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Assignment06Aug {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://awesomeqa.com/practice.html");

        driver.findElement(By.name("firstname")).sendKeys("Sanjay");
        driver.findElement(By.name("lastname")).sendKeys("Kumar");
        driver.findElement(By.id("sex-0")).click();
        driver.findElement(By.id("exp-6")).click();
        driver.findElement(By.id("datepicker")).sendKeys("09/08/2023");
        driver.findElement(By.id("profession-1")).click();

        WebElement dropdown = driver.findElement(By.id("continents"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Antartica");

        WebElement scrolldropdown = driver.findElement(By.id("selenium_commands"));
        Select selectScroll = new Select(scrolldropdown);
        selectScroll.selectByVisibleText("WebElement Commands");

        driver.findElement(By.id("photo")).sendKeys("/Users/sanjaykumar/Desktop/irctc.png");
        /*WebElement link = driver.findElement(By.xpath("//a[text()='Click here to Download File']"));
        Actions action = new Actions(driver);
        action.contextClick(link).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ENTER).build().perform();*/

        driver.findElement(By.id("submit")).click();

        //driver.quit();;


    }
}

