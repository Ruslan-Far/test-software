package org.lab4.cucumber.stepdefs;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import org.lab4.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static com.codeborne.selenide.Selenide.*;
import org.junit.Assert;

import static io.restassured.RestAssured.*;
import static io.restassured.path.json.JsonPath.given;
import static org.junit.Assert.assertTrue;
import io.restassured.response.Response;
import org.lab4.services.EndpointsClass;
import org.lab4.utils.ServicesUtilsClass;

import static io.restassured.path.json.JsonPath.from;

public class TestYandex
{
    public static LoginPage loginPage;
    public static ProfilePage profilePage = new ProfilePage();
    public static MailPage mailPage = new MailPage();
    public static DiskPage diskPage = new DiskPage();
    public static ServicesPage servicesPage = new ServicesPage();

    public static File file;

    private Response response;

    @Given("я нахожусь на странице входа в Яндекс")
    public static void open_yandex()
    {
        Configuration.proxyEnabled = false;
        Configuration.downloadsFolder = "C:\\3_course_5_semester\\test_software\\lab4\\src\\test\\java\\org\\lab4\\disk";
        Configuration.fileDownload = FileDownloadMode.FOLDER;
        loginPage = Selenide.open(ConfProperties.getProperty("loginPage"), LoginPage.class);
    }

    @When("я ввожу в поле {string} что-нибудь")
    public void input(String str)
    {
        if (str.equals("Телефон или почта"))
            loginPage.inputLogin(ConfProperties.getProperty("login"));
        else if (str.equals("Пароль"))
            loginPage.inputPassword(ConfProperties.getProperty("password"));
    }

    @And("я нажимаю на кнопку {string}")
    public void click(String str) throws FileNotFoundException {
        if (str.equals("Войти"))
            loginPage.clickButton();
        else if (str.equals("Мои сервисы"))
            profilePage.clickServices();
        else if (str.equals("Картинка"))
            profilePage.clickImage();
        else if (str.equals("Почта"))
            profilePage.clickMail();
        else if (str.equals("Диск"))
            servicesPage.clickDisk();
        else if (str.equals("Загрузить"))
            diskPage.clickUpload();
        else if (str.equals("Файл"))
            diskPage.clickFiles();
        else if (str.equals("Иконка файла"))
            diskPage.clickIconFile();
        else if (str.equals("Скачать"))
            file = diskPage.clickDownload();
    }

    @Then("я на странице {string}")
    public void onPage(String str)
    {
        if (str.equals("Профиль"))
        {
            Wait();
            String endpoint = String.format(EndpointsClass.GET_BODY, "profile");
            response = ServicesUtilsClass.execute(endpoint, ServicesUtilsClass.HttpMethod.GET);
            Assert.assertTrue(profilePage.isProfilePage());
            closeWebDriver();
        }
        else if (str.equals("Мои сервисы"))
        {
            Wait();
            String endpoint = String.format(EndpointsClass.GET_BODY, "profile/services");
            response = ServicesUtilsClass.execute(endpoint, ServicesUtilsClass.HttpMethod.GET);
            assertTrue(servicesPage.isServicesPage());
            closeWebDriver();
        }
        else if (str.equals("Почта"))
        {
            Wait();
            assertTrue(mailPage.isMailPage());
            closeWebDriver();
        }
    }

    @Then("файл {string}")
    public void checkFile(String str) throws IOException {
        if (str.equals("загрузился на диск"))
        {
            Wait();
            assertTrue(diskPage.isDisplayed());
            closeWebDriver();
        }
        else if (str.equals("выгрузился"))
        {
            Wait();
            assertTrue(diskPage.isDownloaded(file, "Задание 1."));
            closeWebDriver();
        }
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


