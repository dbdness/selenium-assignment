import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

class SeleniumExample {
    private WebDriver webDriver;
    private final String url = "http://localhost:3000/";
    private final String resetUrl = "http://localhost:3000/reset";

    public SeleniumExample(WebDriver webDriver) {
        this.webDriver = webDriver;

        //Loads the page into the driver.
        webDriver.get(url);
    }

    public int getNumberOfRows() {
        List<WebElement> table = webDriver.findElements(By.xpath("//tbody/tr"));
        return table.size();
    }

    public void filterTable(String filter){
        WebElement filterInput = webDriver.findElement(By.id("filter"));
        filterInput.sendKeys(filter);
    }

    public void clearFilter(){
        WebElement filterInput = webDriver.findElement(By.id("filter"));
        filterInput.clear();
    }

    public void sortByYear(){
        WebElement yearSort = webDriver.findElement(By.id("h_year"));
        yearSort.click();
    }

    public void editTopCarDesc(String newDesc){
        //WebElement topRow = webDriver.findElement(By.xpath("//tbody/tr[td='936']"));
        WebElement topRow = webDriver.findElements(By.xpath("//tbody/tr")).get(0);
        WebElement editTag = topRow.findElements(By.tagName("a")).get(0);

        editTag.click();

        WebElement descInput = webDriver.findElement(By.id("description"));
        descInput.clear();
        descInput.sendKeys(newDesc);

        WebElement saveButton = webDriver.findElement(By.id("save"));
        saveButton.click();
    }

    public String getTopCarDesc(){
        WebElement topRow = webDriver.findElements(By.xpath("//tbody/tr")).get(0);
        WebElement carDesc = topRow.findElements(By.xpath("//td")).get(5); //Fifth row element is the description pane

        return carDesc.getText();
    }

    public String getErrorMessage(){
        WebElement newCarButton = webDriver.findElement(By.id("new"));
        newCarButton.click();

        WebElement saveCarButton = webDriver.findElement(By.id("save"));
        saveCarButton.click();

        WebElement submitErr = webDriver.findElement(By.id("submiterr"));

        return submitErr.getText();
    }

    public void addNewCar(String year, String registered, String make, String model, String desc, String price){
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
