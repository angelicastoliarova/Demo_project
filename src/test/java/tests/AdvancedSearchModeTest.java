package tests;

import com.codeborne.selenide.Condition;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AdvancedSearchPage;
import pages.HomePage;
import steps.AdvancedSearchModeSteps;

public class AdvancedSearchModeTest extends BaseTest {
    AdvancedSearchModeSteps advancedSearchModeSteps;

    @BeforeClass
    public void setUp() {
        super.setUp();
        advancedSearchModeSteps = new AdvancedSearchModeSteps();
    }

    @Test
    public void advancedSearchModeTest() {
        String searchingText = "Жил-был пёс";
        String documentType = "документальный";
        String historyType = "история";
        String familyType = "семейный";
        advancedSearchModeSteps.fillAdvancedSearch(documentType, historyType, familyType);
        advancedSearchModeSteps.submitButton();
        advancedSearchModeSteps.verifyFirstFoundMovie(searchingText);
    }
}
