package pages;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    public HomePage() {
    }
    public static String advancedSearchLocator = ".nsbkofuuSifuaoskfpZh2";

    public AdvancedSearchPage clickAdvancedSearch() {
        $(advancedSearchLocator).click();
        return new AdvancedSearchPage();
    }
    public Top250Page clickTop250Page() {
        $(advancedSearchLocator).click();
        return new Top250Page();
    }
}
