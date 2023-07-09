package org.example;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.*;

import org.openqa.selenium.devtools.v114.network.Network;
import org.openqa.selenium.devtools.v114.network.model.Request;
import org.openqa.selenium.devtools.v114.network.model.Response;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;



public class SanityTests {

    public static WebDriver driver;
    Actions action = new Actions(driver);

    MainPage mainPage= new MainPage(driver);

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
    static List<ResponseData> responses = new ArrayList<>();

    @BeforeClass
    public static void start() throws Exception {
        System.setProperty(config.get("driverType").toString(), config.get("driverLocation").toString());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("w3c", true);
        driver = new ChromeDriver(options);
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        devTools.addListener(Network.responseReceived(), event -> {
            Response response = event.getResponse();
            String url = response.getUrl();
            int statusCode = response.getStatus();
            responses.add(new ResponseData(url, statusCode));
        });

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
        mainPage.setEmail(config.get("userName").toString());
        mainPage.setPassword(config.get("password").toString());
        mainPage.submitButtonClick();
        for (ResponseData responseData : responses) {
            if (responseData.getUrl().equals(config.get("urlForTest").toString())) {
                int statusCode = responseData.getStatusCode();
                // Assert the status code
                assert statusCode == config.get("statusCode_404").hashCode() : "Expected status code 404 but found " + statusCode;
            }
        }
    }


    @AfterClass
    public static void after (){
        driver.quit();

    }
}