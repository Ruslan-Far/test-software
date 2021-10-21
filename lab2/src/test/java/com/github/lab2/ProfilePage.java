package com.github.lab2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage
{
    public WebDriver driver;

    public ProfilePage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void clickMyServices()
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("https://passport.yandex.ru/profile"));
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/aside/nav/ul/li[3]/a")).click();
    }

    public boolean isServices()
    {
        String currentURL = driver.getCurrentUrl();
        String URL = "https://passport.yandex.ru/profile/services";
        return URL.equals(currentURL);
    }

    public void entryMenu()
    {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/header/div[2]/div[2]/div/div/a[1]/div")).click();
    }

    public void clickMail()
    {
        driver.findElement(By.xpath("//*[@class=\"menu__text\"]")).click();
    }

    public boolean isMail()
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("https://mail.yandex.ru/?uid=991050095#inbox"));
        String currentURL = driver.getCurrentUrl();
        String URL = "https://mail.yandex.ru/?uid=991050095#inbox";
        return URL.equals(currentURL);
    }
}
