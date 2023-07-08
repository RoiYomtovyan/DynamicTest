package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class MainPage {

    public static WebElement element;


    public static WebElement emailField (WebDriver driver) {
        element=driver.findElement(By.id("email"));
        return element;
    }

    public static WebElement passwordField (WebDriver driver) {
        element=driver.findElement(By.id("password"));
        return element;
    }

    public static WebElement submitButton (WebDriver driver) {
        element=driver.findElement(By.cssSelector("div.d-flex.justify-content-center button.btn.btn-primary.prim.align-center.pr-5.pl-5.pt-2.pb-2"));
        return element;
    }

    public static String verifyResponse() {
        String requestBody = "{\"email\": \"AVIVITYOMTOVYAN@GMAIL.CO.IL\",\"password\": \"123456\"}";

        return response;

    }


}
