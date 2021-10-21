package com.github.lab2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{
    public WebDriver driver;

    public LoginPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void inputLogin(String login)
    {
        driver.findElement(By.id("passp-field-login")).sendKeys(login);
    }

    public void clickLoginBtn()
    {
        driver.findElement(By.id("passp:sign-in")).click();
    }

    public void inputPasswd(String passwd)
    {
        driver.findElement(By.id("passp-field-passwd")).sendKeys(passwd);
    }
}