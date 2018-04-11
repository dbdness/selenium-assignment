import com.jayway.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
    public void verifyLoadedTest() {
        int numberOfRows = seleniumExample.getNumberOfRows();
        assertThat(numberOfRows, is(5));
    }

    @Test
    public void filterTest() {
        seleniumExample.filterTable("2002");
        int numberOfRows = seleniumExample.getNumberOfRows();

        assertThat(numberOfRows, is(2));
    }

    /*
    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
    */

    @AfterClass
    public static void afterAll() {
        webDriver.quit();
    }

}