import com.jayway.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver();
        SeleniumExample seleniumExample = new SeleniumExample(driver);

        RestAssured.get("http://localhost:3000/reset");

        seleniumExample.sortByYear();
        //System.out.println(seleniumExample.getTopCarDesc());
        seleniumExample.editTopCarDesc("Cool car");
        //System.out.println(seleniumExample.getErrorMessage());
        System.out.println(seleniumExample.getTopCarDesc());
        Thread.sleep(5000); //To verify behavior
        driver.quit();
    }
}
