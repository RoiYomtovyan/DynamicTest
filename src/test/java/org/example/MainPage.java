package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainPage {

    public static WebDriver driver;
//    public WebDriverWait wait = new WebDriverWait(driver, 30);

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    static By email = By.id("email");
    static By password = By.id("password");
    static By submit = By.cssSelector("div.d-flex.justify-content-center button.btn.btn-primary.prim.align-center.pr-5.pl-5.pt-2.pb-2");



    public void setEmail (String emailInput){
//        wait.until(ExpectedConditions.titleIs("CocktaiLale"));
        driver.findElement(email).click();
        driver.findElement(email).sendKeys(emailInput);
    }

    public void setPassword (String passwordInput){
        driver.findElement(password).click();
        driver.findElement(password).sendKeys(passwordInput);
    }

    public void submitButtonClick () {
        driver.findElement(submit).click();
    }



}
