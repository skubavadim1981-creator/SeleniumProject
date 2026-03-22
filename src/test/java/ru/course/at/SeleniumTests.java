package ru.course.at;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SeleniumTests {

    private WebDriver driver;

    @BeforeEach
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com/");
    }

    @AfterEach
    public void tearDown(){driver.quit();}

    @Test
    public void search() throws InterruptedException {
        String input = "Selenium";
        WebElement searchField = driver.findElement(By.cssSelector("#sb_form_q"));
        searchField.sendKeys(input);
        searchField.submit();

        WebElement seleniumLink = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[contains(@class,'tilk') and .//cite[contains(.,'selenium.dev')]]")
                ));

        seleniumLink.click();

        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.or(
                        ExpectedConditions.urlContains("selenium.dev"),
                        ExpectedConditions.titleContains("Selenium")
                ));

        assertTrue(
                driver.getCurrentUrl().contains("selenium.dev")
                        || driver.getTitle().contains("Selenium")
        );

//        List<WebElement> results = driver.findElements(By.xpath("//a[contains(@class, 'tilk')][contains(@href, 'selenium.dev')]"));
//
//        clickElement(results, 0);
//        driver.getCurrentUrl();
//
//        assertEquals("https://www.selenium.dev/", driver.getCurrentUrl());List
//
//        List<WebElement> results = driver.findElements(
//                By.xpath("/a[contains(@href,'selenium.dev') and .//h3[normalize-space()='Selenium']]")
//        );
//
//        assertFalse(results.isEmpty(), "Ссылка на selenium.dev не найдена");
//
//        results.get(0).click();
//
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.urlContains("selenium.dev"));
//
//        assertTrue(driver.getCurrentUrl().contains("selenium.dev"));*/
//
//        WebElement searchPageField = driver.findElement(By.cssSelector("#sb_form_q"));
//        assertEquals(input, searchPageField.getAttribute("value"));public
//    }
//    public void clickElement(List<WebElement> results, int num){
//        results.get(num).click();
    }
}
