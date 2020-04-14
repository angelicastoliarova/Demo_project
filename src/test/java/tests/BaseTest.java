package tests;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.open;


public class BaseTest {
    public void setUp() {
        Configuration.browser = Browsers.CHROME;
        Configuration.startMaximized = true;
        Configuration.browserSize = "1920x1080";
        Configuration.screenshots = true;
        Configuration.reportsFolder = "build/reports/tests";
        open("https://www.kinopoisk.ru");
    }
}
