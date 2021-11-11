package org.lab4;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage
{
    public void clickServices()
    {
        $(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/nav/ul/li[3]/a")).click();
    }

    public void clickImage()
    {
        $(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/header/div[2]/div[2]/div/div/a[1]")).click();
    }

    public void clickMail()
    {
        $(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/header/div[2]/div[2]/div/div/div/ul/ul/li[1]")).click();
    }

    public boolean isProfilePage()
    {
        return WebDriverRunner.url().contains(ConfProperties.getProperty("profilePage"));
    }
}
