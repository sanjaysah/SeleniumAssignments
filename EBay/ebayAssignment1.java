package com.thetestingacademy.july22;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ebayAssignment1 {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        driver.get("https://www.ebay.com/b/PC-Desktops-All-In-One-Computers/179/bn_661752");
        WebElement brandFilter = driver.findElement(By.xpath("//button[@class='x-flyout__button']/span[text()='Brand']"));
        brandFilter.click();
        WebElement seeAll = driver.findElement(By.xpath("//button[@class='x-flyout__button']/span[text()='Brand']/../following-sibling::div/div[@class='brm__aspect-footer']/button[text()='see all']"));
        seeAll.click();
        
        //Fluent wait
        WebElement brandOptionsAll = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver input) {
                return driver.findElement(By.xpath("//span[@class='field']/label/span[text()='ABS Computer Technologies']"));
            }
        });

        WebElement ABCCompCheckBox = driver.findElement(By.xpath("//span[@class='field']/label/span[contains(text(),'ABS Computer')]"));
        ABCCompCheckBox.click();
        //Click Apply button
        WebElement applyButton = driver.findElement(By.xpath("//button[@aria-label='Apply']"));
        applyButton.click();

        //Fluent wait
        WebElement noofResultsLabel = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver input) {
                return driver.findElement(By.xpath("//h2[normalize-space()='47 Results']"));
            }
        });
        //h2[normalize-space()='47 Results']
        //h2[@class='srp-controls__count-heading']

        // Print the no of results of applied filter
        String result = noofResultsLabel.getText();
        System.out.println("Total no of Results are - "+result);


    }
}

