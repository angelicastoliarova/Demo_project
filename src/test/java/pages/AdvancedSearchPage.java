package pages;

import com.codeborne.selenide.Condition;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AdvancedSearchPage {
    static public String countrySB = ".__countrySB__";
    static public String genreSB = ".__genreSB__";
    static public String submit = ".submit";

    public AdvancedSearchPage() {
    }

    public void selectSovietUnionCountry() {
        $(countrySB).selectOptionByValue("13");
    }

    public void selectGenreSB(String... typeMovie) {
        Arrays.asList(typeMovie).forEach(tp -> $(genreSB).selectOption(typeMovie));
    }

    public void clickSubmitButton() {
        $(submit).click();
    }

    public void verifyFirstFindedMovie(String searchingText) {
        $$(".info .name .js-serp-metrika").first().shouldHave(Condition.text(searchingText));
    }
}
