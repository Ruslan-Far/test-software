package com.github.lab2;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import static com.codeborne.selenide.Selenide.*;
//import static com.codeborne.selenide.Condition.*;
//
//import java.util.concurrent.TimeUnit;
//
//public class TestYandex
//{
//    public static LoginPage loginPage;
//    public static ProfilePage profilePage;
//    public static WebDriver driver;
//
////    @Before
////    public void setup()
////    {
////        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
////        driver = new ChromeDriver();
////        loginPage = new LoginPage(driver);
////        profilePage = new ProfilePage(driver);
////        driver.manage().window().maximize();
////        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
////        driver.get(ConfProperties.getProperty("loginpage"));
//
////    }
//
//    @Test
//    public void loginServicesTest()
//    {
//        open(ConfProperties.getProperty("loginpage"));
//        $("#passp-field-login").setValue(ConfProperties.getProperty("login")).pressEnter();
//        $(".Field-label").shouldHave(text("Введите пароль"));
//        $("#passp-field-passwd").setValue(ConfProperties.getProperty("password")).pressEnter();
//        $(".personal-info__first").shouldBe(visible);
////        $(".personal-info__first").should(exist);
//
//        //$("#passp:sign-in").click();
//        //$(".avatar__image  avatar__image-server0").should(visible);
//        //$(By.id("passp:sign-in")).click();
//
////        loginPage.inputLogin(ConfProperties.getProperty("login"));
////        loginPage.clickLoginBtn();
////        loginPage.inputPasswd(ConfProperties.getProperty("password"));
////        loginPage.clickLoginBtn();
////        profilePage.clickMyServices();
////        Assert.assertTrue(profilePage.isServices());
////        Wait();
//    }
//
////    @Test
////    public void loginMailTest()
////    {
////        loginPage.inputLogin(ConfProperties.getProperty("login"));
////        loginPage.clickLoginBtn();
////        loginPage.inputPasswd(ConfProperties.getProperty("password"));
////        loginPage.clickLoginBtn();
////        profilePage.entryMenu();
////        profilePage.clickMail();
////        Assert.assertTrue(profilePage.isMail());
////    }
////
////    @After
////    public void end()
////    {
////        driver.quit();
////    }
//
//    public void Wait()
//    {
//        try
//        {
//            Thread.sleep(5000);
//        }
//        catch (InterruptedException e)
//        {
//            e.printStackTrace();
//        }
//    }
//}


//package com.github.lab2;

//import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.*;
import com.codeborne.selenide.impl.FileContent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


public class TestYandex {
    @BeforeClass
    public static void downloadSetup() {
        Selenide.closeWebDriver();
        //установка стандартной папки для скачивания файлов
        Configuration.downloadsFolder = "src/test/java/com/github/lab2/disk";
        Configuration.fileDownload = FileDownloadMode.FOLDER;
        Configuration.proxyEnabled = false;
    }
    @Before
    public void setup() {
        closeWebDriver();
        //открытие страницы входа
        open(ConfProperties.getProperty("loginpage"));
        //ввод данных
        $(By.id("passp-field-login")).setValue("+79273527207");
        $(By.id("passp:sign-in")).click();
        $(By.id("passp-field-passwd")).setValue(ConfProperties.getProperty("password"));
        $(By.id("passp:sign-in")).click();
    }

    @org.junit.Test
    public void mailTest() {
        //вход в почту
        $(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/header/div[2]/div[2]/div/div")).click();
        $(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/header/div[2]/div[2]/div/div/div/ul/ul/li[1]/a")).click();
        //проверка на то, появился ли элемент со страницы почты
        $(By.xpath("//*[@id=\"js-apps-container\"]/div[2]/div[8]/div/div[2]/div/div/div[3]/button[2]")).should(appear);
        //проверка url
        Assert.assertEquals("https://mail.yandex.ru/?uid=926753213#inbox", WebDriverRunner.getWebDriver().getCurrentUrl());
    }
    @org.junit.Test
    public void servicesTest() {
        //вход в сервисы
        $(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/nav/ul/li[3]/a")).click();
        //проверка url
        Assert.assertEquals("https://passport.yandex.ru/profile/services", WebDriverRunner.getWebDriver().getCurrentUrl());
    }

//    @org.junit.Test
//    public void diskDownLoad() throws IOException {
//        //вход в диск
//        $(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/header/div[2]/div[2]/div/div")).click();
//        $(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/header/div[2]/div[2]/div/div/div/ul/ul/li[3]/a")).click();
//        File certificateFile = null;
//        //скачивание файла\
//        $(By.xpath("//*[@id=\"app\"]/div/div/div[4]/div[2]/div/div/div[3]/div/div[5]")).click();
//        certificateFile = $(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div[2]/button[1]")).download();
//        PDF certificatePdf = new PDF(certificateFile);
//        Assert.assertThat(certificatePdf, PDF.containsText("Сертификат о вакцинации COVID-19"));
//        //Assert.assertTrue(fileContent.contains("Сертификат о вакцинации COVID-19"));
//    }
}

