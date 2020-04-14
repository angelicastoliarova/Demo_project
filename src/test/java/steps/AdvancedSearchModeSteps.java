package steps;

import io.qameta.allure.Step;
import pages.AdvancedSearchPage;
import pages.HomePage;

public class AdvancedSearchModeSteps {
    AdvancedSearchPage advancedSearchPage;

    public AdvancedSearchModeSteps() {
    }

    @Step("Fill advanced search filds")
    public void fillAdvancedSearch(String... typeMovie) {
        advancedSearchPage = new HomePage().clickAdvancedSearch();
        advancedSearchPage.selectSovietUnionCountry();
        advancedSearchPage.selectGenreSB(typeMovie);
    }

    @Step("Click Submit button on Advanced Search Page")
    public void submitButton() {
        advancedSearchPage.clickSubmitButton();
    }

    @Step("Verify name of first found movie")
    public void verifyFirstFoundMovie(String searchingText) {
        advancedSearchPage.verifyFirstFindedMovie(searchingText);
    }
}
