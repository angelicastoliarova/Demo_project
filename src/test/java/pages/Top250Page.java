package pages;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Top250Page {
    private WebTable filmsTable;
    public static String filmsTableLocator = ".js-rum-hero";

    public  Top250Page() {
        this.filmsTable = new WebTable(getWebDriver(), filmsTableLocator);
    }

    public List<String> getUiTopMovies() {
        filmsTable.initTable();
        return filmsTable.getAllBodyRows().stream().skip(1).map(WebElement::getText).collect(Collectors.toList());
    }
}
