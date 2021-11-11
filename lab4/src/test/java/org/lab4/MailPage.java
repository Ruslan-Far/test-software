package org.lab4;

import com.codeborne.selenide.WebDriverRunner;

public class MailPage
{
    public boolean isMailPage()
    {
        return WebDriverRunner.url().equals(ConfProperties.getProperty("mailPage"));
    }
}
