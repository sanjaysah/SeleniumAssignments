package com.thetestingacademy.july22;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class ShadowDOMAssignment {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://selectorshub.com/xpath-practice-page/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        Actions action = new Actions(driver);

        Thread.sleep(5000); // If thread is used, works perfectly fine, no other wait needed
      // Below code moves to element near to shadowdom elements but WebElement "learHUBLinkSD" return null.
        /*String path ="//p[contains(text(),'//svg[@iconid=’editon’]')]";
        WebElement waitElement = driver.findElement(By.xpath(path));
        wait.until(ExpectedConditions.visibilityOf(waitElement));
        action.scrollToElement(waitElement).perform();*/

        // Retrieve element within Shadow DOm using JavaScriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String learHUBLinkSDPath = "return document.querySelector('div#userName').shadowRoot.querySelector('a.learningHub')";
        WebElement learHUBLinkSD = (WebElement) js.executeScript(learHUBLinkSDPath);
        wait.until(ExpectedConditions.visibilityOf(learHUBLinkSD));

        //Get href value and print
        String href = learHUBLinkSD.getAttribute("href");
        System.out.println(href);

        //Move to Element and click it using Action class
        String parentWindow = driver.getWindowHandle();
        action.scrollToElement(learHUBLinkSD).moveToElement(learHUBLinkSD).click().perform();

        // Switch windows and return to parent window
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> winds = windows.iterator();
        while(winds.hasNext()){
            String window = winds.next();
            driver.switchTo().window(window);
        }
        driver.switchTo().window(parentWindow);
    }
}
