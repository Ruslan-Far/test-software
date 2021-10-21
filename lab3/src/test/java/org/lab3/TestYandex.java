package org.lab3;

import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.IOException;
import static com.codeborne.selenide.Selenide.*;

public class TestYandex
{
    public static LoginPage loginPage;
    public static ProfilePage profilePage;

    @BeforeClass
    public static void downloadSetup()
    {
        Configuration.proxyEnabled = false;
        Configuration.downloadsFolder = "C:\\3_course_5_semester\\test_software\\lab3\\src\\test\\java\\org\\lab3\\disk";
        Configuration.fileDownload = FileDownloadMode.FOLDER;
    }

    @Before
    public void setup()
    {
        closeWebDriver();
        loginPage = open(ConfProperties.getProperty("loginPage"), LoginPage.class);
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        loginPage.inputPassword(ConfProperties.getProperty("password"));
        profilePage = loginPage.getProfilePage();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @Test
    public void mailTest()
    {
        profilePage.clickMail();
        Wait();
    }

    @Test
    public void servicesTest()
    {
        profilePage.clickServices();
        Wait();
    }

    @Test
    public void diskDownLoad() throws IOException
    {
        profilePage.downloadFile("Лабораторная работа 6. EntityFramework");
        Wait();
    }

    @Test
    public void diskUpload()
    {
        profilePage.uploadFile();
        Wait();
    }

    public void Wait()
    {
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
