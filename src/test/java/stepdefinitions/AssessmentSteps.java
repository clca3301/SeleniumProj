package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class AssessmentSteps {
    WebDriver driver;

    @Given("User Opens the assessment page")
    public void user_opens_assessment_page() throws InterruptedException {
        driver = Hooks.getDriver();
        String relativePath = "src/test/resources/html/QE-index.html";
        File file = new File(relativePath);
        String absolutePath = file.getAbsolutePath();
        driver.get("file://" + absolutePath);

        //wait page load complete
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));  // Timeout after 30 seconds
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

    }

    @And("Email address input, password inputs and login button are present")
    public void email_password_login_are_present() {
        driver = Hooks.getDriver();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='inputEmail']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='inputPassword']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//button[text()='Sign in']")).isDisplayed());
    }

    @Then("Enter email and password")
    public void enter_email_pw() throws InterruptedException {
        driver = Hooks.getDriver();
        driver.findElement(By.xpath("//input[@id='inputEmail']")).sendKeys("abc@gmail.com");
        driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys("12345678");
    }

    @And("Assert that there are 3 values in the listgroup")
    public void Assert_3_values_in_the_listgroup() {
        driver = Hooks.getDriver();
        List<WebElement> ListGroupValues = driver.findElements(By.xpath("//div[@id='test-2-div']/ul[@class='list-group']/li"));
        System.out.println("Number of values in the list group: " + ListGroupValues.size());
        Assert.assertEquals(ListGroupValues.size(), 3);
    }

    @And("Assert that the second list item's value is set to 'List Item 2'")
    public void assert_2nd_item_is_List_item_2(){
        driver = Hooks.getDriver();
        List<WebElement> ListGroupValues = driver.findElements(By.xpath("//div[@id='test-2-div']/ul[@class='list-group']/li"));

        // Use JavaScript to get the direct text of the <li> element (excluding child elements like <span>)
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String secondListItemText = (String) js.executeScript("return arguments[0].childNodes[0].nodeValue.trim();", ListGroupValues.get(1));
        System.out.println("Second list item value: " + secondListItemText);

        Assert.assertEquals(secondListItemText,"List Item 2");
    }

    @Then("Assert second badge value is 6")
    public void assert_2nd_badge_value_is_6(){
        driver = Hooks.getDriver();
        List<WebElement> ListGroupBadgeValues = driver.findElements(By.xpath("//ul[@class='list-group']/li/span[@class='badge badge-pill badge-primary']")) ;
        System.out.println("Second badge value: " + ListGroupBadgeValues.get(1).getText());
        Assert.assertEquals("6",ListGroupBadgeValues.get(1).getText());
    }

    @And("Assert that 'Option 1' is the default selected value")
    public void assert_default_value_is_option1(){
        driver = Hooks.getDriver();
        String defaultValue = driver.findElement(By.xpath("//button[@id='dropdownMenuButton']")).getText();
        System.out.println("Default value: "+defaultValue);
        Assert.assertEquals(defaultValue,"Option 1");
    }

    @Then("Select Option 3")
    public void select_option3() throws InterruptedException {
        driver = Hooks.getDriver();
        driver.findElement(By.xpath("//button[@id='dropdownMenuButton']")).click();
        driver.findElement(By.xpath("//a[@class='dropdown-item' and text()='Option 3']")).click();
        System.out.println("Current value: " + driver.findElement(By.xpath("//button[@id='dropdownMenuButton']")).getText());
        Assert.assertEquals("Option 3", driver.findElement(By.xpath("//button[@id='dropdownMenuButton']")).getText());
    }

    @Then("First button is enabled")
    public void first_btn_enabled(){
        driver = Hooks.getDriver();
        Assert.assertTrue(driver.findElement(By.xpath("(//div[@id='test-4-div']/button)[1]")).isEnabled());
    }

    @And("Second button is disabled")
    public void second_btn_disabled(){
        driver = Hooks.getDriver();
        Assert.assertFalse(driver.findElement(By.xpath("(//div[@id='test-4-div']/button)[2]")).isEnabled());
    }

    @And("Wait for button to be displayed and click")
    public void wait_btn_and_click(){
        driver = Hooks.getDriver();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='test5-button']")));
        driver.findElement(By.xpath("//button[@id='test5-button']")).click();
    }

    @Then("Success message is displayed")
    public void success_msg_shown(){
        driver = Hooks.getDriver();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'You clicked a button!')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'You clicked a button!')]")).isDisplayed());
    }

    @And("Button is disabled")
    public void btn_disabled(){
        driver = Hooks.getDriver();
        Assert.assertFalse(driver.findElement(By.xpath("//button[@id='test5-button']")).isEnabled());
    }

    //method to get cell value in div6 Grid (coordinates 0,0 to 3,3)
    public String getCellValue(int row, int col){
        driver = Hooks.getDriver();
        row += 1;
        col += 1;
        String cellXpath = "//div[@id='test-6-div']//table//tbody//tr["+ row + "]//td["+ col +"]";
        return driver.findElement(By.xpath(cellXpath)).getText();
    }

    @And("Find the value of cell at coordinates 2,2 and aseert it is 'Ventosanzap'")
    public void find_cell_value_at_2_2(){
        String cellValue = getCellValue(2,2);
        System.out.println("Value of cell 2,2: " + cellValue);
        Assert.assertEquals(cellValue,"Ventosanzap");
    }
}
