package org.example;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.io.FileReader;
import java.io.IOException;


public class SanityTests {

    public static WebDriver driver;
    Actions action = new Actions(driver);

    MainPage mainPage= new MainPage(driver);
    WebDriverWait wait = new WebDriverWait(driver, 30);

    // this will allow us to read the test configuration from a file
    static Object file;

    static {
        try {
            file = new JSONParser().parse(new FileReader("config.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    static JSONObject config = (JSONObject) file;

    @BeforeClass
    public static void start() throws Exception {
        System.setProperty(config.get("driverType").toString(), config.get("driverLocation").toString());
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        // this code block is trying to open the web page
        boolean pageOpened = false;
        try {
            driver.navigate().to(config.get("url").toString());
            pageOpened = true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("webpage was not found " + e.getMessage());
            pageOpened = false;
        } finally {
            if (pageOpened) {
                System.out.println("Webpage opened successfully");

            }

        }

    }

    @Test
    public void SanityTest01_insert_non_valid_user_data() {
        mainPage.setEmail("roiyomtovyan@gmail.com");
        mainPage.setPassword("123456");
        mainPage.submitButtonClick();

    }


    @AfterClass
    public static void after (){
        driver.quit();

    }
}