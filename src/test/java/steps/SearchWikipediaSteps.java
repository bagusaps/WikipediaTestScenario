package steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SearchWikipediaSteps {

    WebDriver driver = null;

    @Before
    public void openBrowser(){
        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);
        System.setProperty("webdriver.chrome.driver", dir+"/driver/chromedriver");
        driver = new ChromeDriver();
    }

    @After
    public void closeBrowser(){
        driver.close();
        driver = null;
    }

    @Given("open wikipedia")
    public void openWikipedia() throws InterruptedException{
        driver.get("https://www.wikipedia.org/");
    }


    @Then("user in wikipedia page")
    public void userInWikipediaPage() {
        assert driver.findElement(By.xpath("//span[@class='central-textlogo__image sprite svg-Wikipedia_wordmark']")).isDisplayed();

    }

    @When("user type search {string}")
    public void userTypeSearch(String searchTerm) {
        driver.findElement(By.xpath("//input[@id='searchInput']")).sendKeys(searchTerm);
    }

    @Then("search suggestion should contain {string}")
    public void searchSuggestionShouldContain(String searchTerm) {
        List<WebElement> listSuggestionElement = driver.findElements(By.xpath("//div[@class='suggestions-dropdown']//h3"));
        for(WebElement suggestionElement : listSuggestionElement){
            assert suggestionElement.getText().toLowerCase().contains(searchTerm);
        }

    }

    @When("user click and select language is {string}")
    public void userClickAndSelectLanguageIsBahasaIndonesia(String language) {
        String languageOptionXPath = String.format("//option[@value='%s']", language);
        driver.findElement(By.xpath("//select[@id='searchLanguage']")).click();
        driver.findElement(By.xpath(languageOptionXPath)).click();
    }

    @Then("verify selected language is {string}")
    public void verifySelectedLanguageIs(String languageCode) {
        assert driver.findElement(By.xpath("//label[@id='jsLangLabel']")).getText().toLowerCase().equals(languageCode);
    }
}
