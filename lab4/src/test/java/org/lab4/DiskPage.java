package org.lab4;

import com.codeborne.pdftest.PDF;
import org.openqa.selenium.By;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static com.codeborne.selenide.Selenide.$;

public class DiskPage
{
    public void clickUpload()
    {
        File image = new File(ConfProperties.getProperty("image"));
        $(By.xpath("//*[@id=\"app\"]/div/div/div[3]/div[1]/span[1]/span[2]/div/input")).uploadFile(image);
    }

    public boolean isDisplayed()
    {
        return $(By.xpath("//*[@id=\"app\"]/div/div/div[4]/div[2]/div/div/div[3]/div/div[11]")).isDisplayed();
    }

    public void clickFiles()
    {
        $(By.xpath("//*[@id=\"/disk\"]")).click();
    }

    public void clickIconFile()
    {
        $(By.xpath("//*[@id=\"app\"]/div/div/div[4]/div[2]/div/div/div[3]/div/div[2]")).click();
    }

    public File clickDownload() throws FileNotFoundException {
        return $(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div[2]/button[1]")).download();
    }

    public boolean isDownloaded(File lab, String text) throws IOException
    {
        PDF labPDF = new PDF(lab);
        System.out.println(labPDF.text);
        return labPDF.text.contains(text);
    }
}
