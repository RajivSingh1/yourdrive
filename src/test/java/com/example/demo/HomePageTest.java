package com.example.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageTest {

    @FindBy(css="#home-logout")
    private WebElement logoutButton;

    public HomePageTest(WebDriver webDriver) {
        PageFactory.initElements(webDriver,this);
    }
    public void logout(){
        this.logoutButton.click();
    }


}
