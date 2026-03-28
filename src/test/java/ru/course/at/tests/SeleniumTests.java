package ru.course.at.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTests {

    private WebDriver driver;

    private final By inputField = By.cssSelector("#searchbox_input");
    private final By resultLinks = By.cssSelector("h2 > a[href]");

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://duckduckgo.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchFieldTest() {
        String input = "Selenium";
        WebElement searchField = driver.findElement(inputField);
        searchField.sendKeys(input);
        searchField.submit();

        List<WebElement> result = driver.findElements(resultLinks);
        clickElement(result, 0);

        assertEquals("https://www.selenium.dev/", driver.getCurrentUrl(), "Не та ссылка");
    }

    @Test
    public void searchResultTest() {
        String input = "Selenium";
        WebElement searchField = driver.findElement(inputField);
        searchField.sendKeys(input);

        WebElement searchPageField = driver.findElement(inputField);
        assertEquals(input, searchPageField.getAttribute("value"));
    }

    public void clickElement(List<WebElement> result, int num) {
        result.get(num).click();
    }
}
