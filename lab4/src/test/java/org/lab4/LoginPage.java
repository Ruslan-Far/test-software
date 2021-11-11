package org.lab4;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage
{
    public void inputLogin(String login)
    {
        $("#passp-field-login").setValue(login);
    }

    public void inputPassword(String password)
    {
        $("#passp-field-passwd").setValue(password);
    }

    public void clickButton()
    {
        $(By.id("passp:sign-in")).click();
    }
}
