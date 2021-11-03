package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.*;

public class CheckingArtworkStep {

    HomePage homePage = new HomePage();
    SearchPage searchPage = new SearchPage();
    ArtistPage artistPage = new ArtistPage();
    ArtistReleasesPage artistReleasesPage = new ArtistReleasesPage();
    AlbumSinglePage albumSinglePage = new AlbumSinglePage();

    @Given("I am on Home Page")
    public void iAmOnHomePage() {
        Assert.assertTrue(homePage.isOnPage());
    }

    @When("I tap Search Icon on Navigation Bar")
    public void iTapSearchIconOnNavigationBar() {
        homePage.tapSearchIcon();
    }

    @When("I am on Search Page")
    public void iAmOnSearchPage() {
        Assert.assertTrue(searchPage.isOnPage());
    }

    @When("I input {string} in Search Bar on Search Page")
    public void iInputInSearchBarOnSearchPage(String arg0) {
        searchPage.inputSearchBar(arg0);
    }

    @When("I tap {string} Filter Button on Search Page")
    public void iTapFilterButtonOnSearchPage(String arg0) {
        searchPage.tapFilter(arg0);
    }

    @When("I tap {string} Text in Search Result List on Search Page")
    public void iTapTextInSearchResultListOnSearchPage(String arg0) {
        searchPage.tapArtistOnList(arg0);
    }

    @When("I am on Artist Page of {string}")
    public void iAmOnArtistPageOf(String arg0) {
        Assert.assertTrue(artistPage.isOnPage(arg0));
    }

    @When("I tap See discography Button on Artist Page")
    public void iTapSeeDiscographyButtonOnArtistPage() {
        artistPage.tapSeeDiscoveryButton();
    }

    @When("I am on Artist Releases Page")
    public void iAmOnArtistReleasesPage() {
        Assert.assertTrue(artistReleasesPage.isOnPage());
    }

    @When("I tap {string} Text in Discography List on Artist Releases Page")
    public void iTapTextInDiscographyListOnArtistReleasesPage(String arg0) {
        artistReleasesPage.tapAlbumOrSingle(arg0);
    }

    @When("I am on Album or Single Page of {string}")
    public void iAmOnAlbumOrSinglePageOf(String arg0) {
        Assert.assertTrue(albumSinglePage.isOnPage(arg0));
    }

    @Then("I can see the artwork same as {string}")
    public void iCanSeeTheArtworkSameAs(String arg0) {
        Assert.assertTrue(albumSinglePage.presenceOfArtworkImage(arg0));
    }
}
