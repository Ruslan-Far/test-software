package org.lab3;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage
{
    @Step
    public void inputLogin(String login)
    {
        $("#passp-field-login").shouldBe(visible);
        $("#passp-field-login").setValue(login).pressEnter();
    }

    @Step
    public void inputPassword(String password)
    {
        $("#passp-field-passwd").shouldBe(visible);
        $("#passp-field-passwd").setValue(password).pressEnter();
    }

    public ProfilePage getProfilePage()
    {
        $(".personal-info__first").shouldBe(visible);
        return page(ProfilePage.class);
    }
}