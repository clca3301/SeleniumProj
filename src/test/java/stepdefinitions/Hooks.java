package stepdefinitions;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {
    public static WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    // This method will run after each scenario
    @After
    public void tearDown() {
        // Close the browser after each scenario
        driver.quit();
        System.out.println("Closing browser");
    }
    public static WebDriver getDriver() {
        return driver;
    }
}
