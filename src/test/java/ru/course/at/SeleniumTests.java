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
        driver.get("https://duckduckgo.com/");
    }

    @AfterEach
    public void tearDown(){driver.quit();}

    @Test
    public void search() throws InterruptedException {
        String input = "Selenium";
        WebElement searchField = driver.findElement(By.cssSelector("#searchbox_input"));
        searchField.sendKeys(input);
        searchField.submit();

        Thread.sleep(10000);

        List<WebElement> result = driver.findElements(By.cssSelector("h2 > a[href]"));

        clickElement(result, 0);
        assertEquals("https://www.selenium.dev/", driver.getCurrentUrl(), "Не та ссылка");
    }

    @Test
    public void search2() {
        String input = "Selenium";
        WebElement searchField = driver.findElement(By.cssSelector("#searchbox_input"));
        searchField.sendKeys(input);

        WebElement searchPageField = driver.findElement(By.cssSelector("#searchbox_input"));
        assertEquals(input, searchPageField.getAttribute("value"));
    }

    public void clickElement(List<WebElement> result, int num) {result.get(num).click();}

}
