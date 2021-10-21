package org.lab3;

import com.codeborne.pdftest.PDF;
import org.junit.Assert;
import org.openqa.selenium.By;
import java.io.File;
import java.io.IOException;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProfilePage
{
    public void clickMail()
    {
        $(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/header/div[2]/div[2]/div/div/a[1]/div")).click();
        $(By.xpath("//*[@class=\"menu__text\"]")).click();
        $(By.xpath("//*[@id=\"js-apps-container\"]/div[2]/div[8]/div/div[3]/div[2]/div[2]/div/div[1]/div[1]/a[1]/span")).should(exist);
    }

    public void clickServices()
    {
        $(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/nav/ul/li[3]/a")).click();
        $(By.xpath("//*[@id=\"cloud\"]/div[1]/div")).shouldHave(exactText("Диск"));
    }

    public void downloadFile(String text) throws IOException
    {
        clickServices();
        $(By.xpath("//*[@id=\"cloud\"]/div[1]/div/a")).click();
        $(By.xpath("//*[@id=\"/disk\"]")).click();
        $(By.xpath("//*[@id=\"app\"]/div/div/div[4]/div[2]/div/div/div[3]/div/div[2]")).click();
        File lab = $(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div[2]/button[1]")).download();
        PDF labPDF = new PDF(lab);
        assertThat(labPDF, PDF.containsText(text));
        System.out.println(labPDF.text);
    }

    public void uploadFile()
    {
        clickServices();
        $(By.xpath("//*[@id=\"cloud\"]/div[1]/div/a")).click();
        File image = new File(ConfProperties.getProperty("image"));
        $(By.xpath("//*[@id=\"app\"]/div/div/div[3]/div[1]/span[1]/span[2]/div/input")).uploadFile(image);
        if ($(By.xpath("//*[@id=\"app\"]/div/div/div[4]/div[2]/div/div/div[3]/div/div[11]")).isDisplayed())
            Assert.assertTrue(true);
        else
            Assert.assertTrue(false);
    }
}
