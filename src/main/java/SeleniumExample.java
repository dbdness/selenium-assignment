import com.oracle.tools.packager.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

class SeleniumExample {
    private WebDriver webDriver;
    private final String url = "http://localhost:3000/";
    private final String resetUrl = "http://localhost:3000/reset";

     SeleniumExample(WebDriver webDriver) {
        this.webDriver = webDriver;

        //Loads the page into the driver.
        webDriver.get(url);
    }

     int getNumberOfRows() {
        List<WebElement> table = webDriver.findElements(By.xpath("//tbody/tr"));
        return table.size();
    }

     void filterTable(String filter){
        WebElement filterInput = webDriver.findElement(By.id("filter"));
        filterInput.sendKeys(filter);
    }

     void clearFilter(){
        WebElement filterInput = webDriver.findElement(By.id("filter"));
        filterInput.clear();

        //For some reason, the table doesn't update unless I manually trigger it.
        filterInput.sendKeys(Keys.SPACE);
        filterInput.sendKeys(Keys.BACK_SPACE);
    }

     void sortByYear(){
        WebElement yearSort = webDriver.findElement(By.id("h_year"));
        yearSort.click();
    }

     String getTopCarId(){
        //Retrieves the first td element (id) of the first row element (car).
        WebElement carId = webDriver.findElement(By.xpath("//tr[1]/td[1]"));

        return carId.getText();
    }

     String getBottomCarId(){
        List<WebElement> rows = webDriver.findElements(By.xpath("//tbody/tr"));

        //Retrieves the first td element (id) of the last row element (car).
        WebElement carId = webDriver.findElement(By.xpath(String.format("//tr[%s]/td[1]", rows.size())));

        return carId.getText();
    }

     String getNewestCar(){
        List<WebElement> rows = webDriver.findElements(By.xpath("//tbody/tr"));

        //Retrieves the first td element (id) of the last row element (car).
        String carYear = webDriver.findElement(By.xpath(String.format("//tr[%s]/td[2]", rows.size()))).getText();
        String carReg = webDriver.findElement(By.xpath(String.format("//tr[%s]/td[3]", rows.size()))).getText();
        String carMake = webDriver.findElement(By.xpath(String.format("//tr[%s]/td[4]", rows.size()))).getText();
        String carModel = webDriver.findElement(By.xpath(String.format("//tr[%s]/td[5]", rows.size()))).getText();
        String carDesc = webDriver.findElement(By.xpath(String.format("//tr[%s]/td[6]", rows.size()))).getText();
        String carPrice = webDriver.findElement(By.xpath(String.format("//tr[%s]/td[7]", rows.size()))).getText();

        return String.format("%s - %s - %s %s - %s - %s", carYear, carReg, carMake, carModel, carDesc, carPrice);
    }

     void editTopCarDesc(String newDesc){
        //WebElement topRow = webDriver.findElement(By.xpath("//tbody/tr[td='936']"));
        //WebElement topRow = webDriver.findElements(By.xpath("//tbody/tr")).get(0);
        //WebElement editTag = topRow.findElements(By.tagName("a")).get(0);
        WebElement topRowActions = webDriver.findElement(By.xpath("//tbody/tr[1]/td[8]"));
        WebElement editTag = topRowActions.findElements(By.tagName("a")).get(0);
        editTag.click();

        WebElement descInput = webDriver.findElement(By.id("description"));
        descInput.clear();
        descInput.sendKeys(newDesc);

        WebElement saveButton = webDriver.findElement(By.id("save"));
        saveButton.click();
    }

     String getTopCarDesc(){
        WebElement topRow = webDriver.findElements(By.xpath("//tbody/tr")).get(0);
        //WebElement carDesc = topRow.findElements(By.xpath("//td")).get(5); //Fifth row element is the description pane
        WebElement carDesc = webDriver.findElement(By.xpath("//tbody/tr[1]/td[6]"));
        return carDesc.getText();
    }

     String getErrorMessage(){
        WebElement newCarButton = webDriver.findElement(By.id("new"));
        newCarButton.click();

        WebElement saveCarButton = webDriver.findElement(By.id("save"));
        saveCarButton.click();

        WebElement submitErr = webDriver.findElement(By.id("submiterr"));

        return submitErr.getText();
    }

     void addNewCar(String year, String registered, String make, String model, String desc, String price){
        WebElement newCarButton = webDriver.findElement(By.id("new"));
        newCarButton.click();

        WebElement yearInput = webDriver.findElement(By.id("year"));
        WebElement registeredInput = webDriver.findElement(By.id("registered"));
        WebElement makeInput = webDriver.findElement(By.id("make"));
        WebElement modelInput = webDriver.findElement(By.id("model"));
        WebElement descInput = webDriver.findElement(By.id("description"));
        WebElement priceInput = webDriver.findElement(By.id("price"));

        yearInput.sendKeys(year);
        registeredInput.sendKeys(registered);
        makeInput.sendKeys(make);
        modelInput.sendKeys(model);
        descInput.sendKeys(desc);
        priceInput.sendKeys(price);

        WebElement saveCarButton = webDriver.findElement(By.id("save"));
        saveCarButton.click();
    }

}
