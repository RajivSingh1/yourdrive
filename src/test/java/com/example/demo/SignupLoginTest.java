package com.example.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignupLoginTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    private static final String FIRSTNAME="Dukuze";
    private static final String LASTNAME="Orla";
    private static final String USERNAME="dukuze";
    private static final String PASSWORD="Dukuze01";

    @BeforeAll
    static void beforeAll(){
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    public void beforeEach(){
//        System.setProperty("webdriver.chrome.driver", "/Users/user/Downloads/chromedriver");
        this.driver =new  ChromeDriver();
    }

    @AfterEach
    public void afterEach(){
        if(this.driver != null){
            driver.quit();
        }
    }

    @Test
    public void unAuthorisedUserAccess(){
        driver.get("http://localhost:" + this.port + "/home");
        assertFalse(driver.getTitle() == "Home");

        driver.get("http://localhost:"+this.port+"/login");
        assertEquals("Login",driver.getTitle() );

        driver.get("http://localhost:"+this.port+"/signup");
        assertEquals("Sign Up",driver.getTitle() );
    }
    @Test
    public void testUserSignupAndLogin() throws InterruptedException {

        driver.get("http://localhost:" + this.port + "/signup");

        SignupPageTest signupPage = new SignupPageTest(driver);
        signupPage.signup(FIRSTNAME, LASTNAME, USERNAME, PASSWORD);
        driver.get("http://localhost:" + this.port + "/login");

        LoginPageTest loginPage = new LoginPageTest(driver);
        loginPage.login(USERNAME, PASSWORD);
        assertEquals("Home", driver.getTitle());

        HomePageTest homePage = new HomePageTest(driver);
        Thread.sleep(2000);
        homePage.logout();
        driver.get("http://localhost:" + this.port + "/home");
        assertFalse(driver.getTitle() == "Home");
        assertEquals("Login", driver.getTitle());

    }


}
