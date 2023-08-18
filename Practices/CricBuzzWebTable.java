package com.thetestingacademy.july22;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class CricBuzzWebTable {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        driver.get("https://www.cricbuzz.com/cricket-team/india/2/stats");
        String xpathRows = "//tr[@class='cb-srs-stats-tr']";
        List<WebElement> rowsElements = driver.findElements(By.xpath(xpathRows));

        for(WebElement e: rowsElements){
            String name = "Sachin Tendulkar";
            String xpathName = xpathRows+"/td/a";
            String xpathRuns = xpathRows+"/td[4]";
            WebElement nameElement = driver.findElement(By.xpath(xpathName));
            if(nameElement.getText().equals(name)){
                String runs = driver.findElement(By.xpath(xpathRuns)).getText();
                System.out.println("Total runs scored By - "+name+" is - "+runs);
                break;
            }
        }
    }
}
