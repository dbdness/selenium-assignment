import com.jayway.restassured.RestAssured;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@FixMethodOrder(MethodSorters.JVM)
public class SeleniumExampleTest {
    private static final WebDriver webDriver = new ChromeDriver();
    private SeleniumExample seleniumExample;

    @BeforeClass
    public static void beforeAll() {
        //Reset example site before all.
        RestAssured.get("http://localhost:3000/reset");
    }

    @Before
    public void setUp() throws Exception {
        seleniumExample = new SeleniumExample(webDriver);
    }

    @Test
    public void verifyLoadedTest_01() {
        int numberOfRows = seleniumExample.getNumberOfRows();
        assertThat(numberOfRows, is(5));
    }

    @Test
    public void filterTest_02() {
        seleniumExample.filterTable("2002");
        int numberOfRows = seleniumExample.getNumberOfRows();

        assertThat(numberOfRows, is(2));
    }

    @Test
    public void clearFilterTest_03(){
        seleniumExample.filterTable("2002");
        int numberOfRows = seleniumExample.getNumberOfRows();

        assertThat(numberOfRows, is(2));

        seleniumExample.clearFilter();
        numberOfRows = seleniumExample.getNumberOfRows();
        assertThat(numberOfRows, is(5));
    }

    @Test
    public void sortTest_04(){
        seleniumExample.sortByYear();
        String topCarId = seleniumExample.getTopCarId();
        String bottomCarId = seleniumExample.getBottomCarId();

        assertThat(topCarId, is("938"));
        assertThat(bottomCarId, is("940"));
    }

    @Test
    public void editDescTest_05(){
        seleniumExample.editTopCarDesc("Cool car");

        String topCarDesc = seleniumExample.getTopCarDesc();

        assertThat(topCarDesc, is("Cool car"));
    }

    @Test
    public void fieldErrorTest_06(){
        String bannerError = seleniumExample.getErrorMessage();
        assertThat(bannerError, is("All fields are required"));
    }

    @Test
    public void newCarTest_07(){
        seleniumExample.addNewCar("2008", "2002-5-5", "Kia", "Rio", "As new", "31000");
        int numberOfRows = seleniumExample.getNumberOfRows();
        String newestCar = seleniumExample.getNewestCar();

        assertThat(numberOfRows, is(6));
        assertThat(newestCar, is("2008 - 5/5/2002 - Kia Rio - As new - 31.000,00 kr."));
    }

    @AfterClass
    public static void afterAll() {
        webDriver.quit();
    }

}