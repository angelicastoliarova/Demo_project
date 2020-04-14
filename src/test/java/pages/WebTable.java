package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.codeborne.selenide.Selenide.$;

public class WebTable {
    private static final String TAG_TH = "th";
    private static final String TAG_TR = "tr";
    private static final String TAG_TD = "td";
    private WebDriver driver;

    String tableXpath;
    List<String> columnNames;
    List<WebElement> columnHeaders;
    Map<String, Integer> columnMap;

    public WebTable(WebDriver driver, String xpath) {
        this.driver = driver;
        this.tableXpath = xpath;
    }

    public WebElement getTable() {
        return $(tableXpath);
    }

    /**
     * Initializes table object
     */
    public WebTable initTable() {
        initColumnNames();
        initColumnMap();
        return this;
    }

    /**
     * Initializes list of column headers by tag <th></th>
     * If the column has an empty name, then the name is initialized as a column index.
     */
    private void initColumnNames() {
        columnHeaders = getTable().findElements(By.tagName(TAG_TH));
        columnNames = columnHeaders
                .stream()
                .map(column -> {
                    String columnName = column.getText().trim();
                    if (columnName.isEmpty()) {
                        columnName = String.valueOf(columnHeaders.indexOf(column));
                    }
                    return columnName;
                })
                .collect(Collectors.toList());
    }

    private void initColumnMap() {
        columnMap = IntStream.range(0, columnNames.size()).boxed().collect(Collectors.toMap(columnNames::get, Function.identity()));
    }

    /**
     * Returns list of all row elements in table body
     *
     * @return
     */
    public List<WebElement> getAllBodyRows() {
        return getTable().findElements(By.tagName(TAG_TR)).stream().skip(1).collect(Collectors.toList());
    }
}
