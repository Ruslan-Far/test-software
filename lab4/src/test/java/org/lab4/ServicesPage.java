package org.lab4;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ServicesPage
{
    public void clickDisk()
    {
        $(By.xpath("//*[@id=\"cloud\"]/div[1]/div/a")).click();
    }

    public boolean isServicesPage()
    {
        return WebDriverRunner.url().equals(ConfProperties.getProperty("servicesPage"));
    }
}
