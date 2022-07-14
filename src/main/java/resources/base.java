package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;

public class base {

    public static WebDriver driver;
    public static String resourcesPath = null;
    public static final String CURRENT_DIR = System.getProperty("user.dir");
    public static final String log4j = "log4j2.xml";
    public static String dataprops = "data.properties";

    public WebDriver initializeDriver() throws Exception {
        if (Platform.getCurrent().toString().equalsIgnoreCase("MAC")) {
            resourcesPath = CURRENT_DIR + "//src//main//java//resources//";

        } else if (Platform.getCurrent().toString().contains("WIN")) {
            resourcesPath = CURRENT_DIR + "\\src\\main\\java\\resources\\";

        }

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(resourcesPath + File.separator + dataprops);
        prop.load(fis);
        String browserName = prop.getProperty("browser");
        System.out.println(browserName);

        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            Map<String, Object> chromePreferences = new Hashtable<String, Object>();
            /*
             * Below two chrome preference settings will disable popup dialog when download
             * file.
             */
            chromePreferences.put("profile.default_content_settings.popups", 0);
            chromePreferences.put("download.prompt_for_download", "false");
            chromePreferences.put("profile.default_content_setting_values.automatic_downloads", 1);
            /* Set file save to directory. */
            chromePreferences.put("download.default_directory", CURRENT_DIR);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("--disable-gpu");
            options.addArguments("--start-maximized");
            options.addArguments("enable-automation");
            options.addArguments("--window-size=2560,1440");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-browser-side-navigation");
            options.addArguments("--dns-prefetch-disable");
            options.addArguments("disable-features=NetworkService");
            options.setExperimentalOption("useAutomationExtension", false);
            options.setPageLoadStrategy(PageLoadStrategy.NONE);
            options.setExperimentalOption("prefs", chromePreferences);
            options.setAcceptInsecureCerts(true);
            options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);

            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1900, 1080));
            driver.manage().window().maximize();

        } else if (browserName.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver.manage().window().maximize();

        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;

    }

    public void getScreenshot() throws IOException {

        Screenshot s = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        ImageIO.write(s.getImage(), "PNG", new File("./screenshots" + File.separator + "Screenshot.png"));
        System.out.println("Screenshot Taken!!!!");


    }

}
