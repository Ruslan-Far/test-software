package com.github.lab2;

import com.codeborne.pdftest.PDF;
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


public class Test {
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

    @org.junit.Test
    public void diskDownLoad() throws IOException {
        //вход в диск
        $(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/header/div[2]/div[2]/div/div")).click();
        $(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/header/div[2]/div[2]/div/div/div/ul/ul/li[3]/a")).click();
        File certificateFile = null;
        //скачивание файла\
        $(By.xpath("//*[@id=\"app\"]/div/div/div[4]/div[2]/div/div/div[3]/div/div[5]")).click();
        certificateFile = $(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div[2]/button[1]")).download();
        PDF certificatePdf = new PDF(certificateFile);
        Assert.assertThat(certificatePdf, PDF.containsText("Сертификат о вакцинации COVID-19"));
        //Assert.assertTrue(fileContent.contains("Сертификат о вакцинации COVID-19"));
    }
}
