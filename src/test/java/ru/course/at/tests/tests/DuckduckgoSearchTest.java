package ru.course.at.tests.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.course.at.tests.pages.MainPage;
import ru.course.at.tests.pages.ResultsPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DuckduckgoSearchTest {

    private WebDriver driver;

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
    public void searchResultTest() {
        String input = "Selenium";
        MainPage mp = new MainPage(driver);
        mp.sendText(input);

        ResultsPage rp = new ResultsPage(driver);
        rp.clickElement(0);

        assertEquals("https://www.selenium.dev/", driver.getCurrentUrl(), "Не та ссылка");
    }

    @Test
    public void searchFieldTest() {
        String input = "Selenium";
        MainPage mp = new MainPage(driver);
        mp.sendText(input);

        ResultsPage rp = new ResultsPage(driver);
        assertEquals(input, rp.getTextFromSearchField(), "Текст не совпал");
    }
}
