package com.example.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPageTest {
    @FindBy(css="#inputFirstName")
    private WebElement firstNameField;

    @FindBy(css="#inputLastName")
    private WebElement lastNameField;

    @FindBy(css="#inputUsername")
    private WebElement usernameNameField;

    @FindBy(css="#inputPassword")
    private WebElement passwordField;

    @FindBy(css="#submit-button")
    private WebElement submitButton;

    public SignupPageTest(WebDriver webDriver) {
        PageFactory.initElements(webDriver,this);
    }

    public void signup(String firstName, String lastName, String userName, String password){
        this.firstNameField.sendKeys(firstName);
        this.lastNameField.sendKeys(lastName);
        this.passwordField.sendKeys(password);
        this.usernameNameField.sendKeys(userName);
        this.submitButton.click();
    }

}

