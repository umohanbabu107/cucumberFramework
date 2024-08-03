package base;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.time.Duration;
import java.util.Properties;
import java.util.stream.Stream;

public class BaseClass {
    private Properties properties;
    protected static WebDriver driver;

    @Before
    public void driverSetup(){
        driver = this.initializeDriver();
        this.configureDriver(driver);
    }

    @After
    public void driverQuit(){
        if(driver != null){
            driver.quit();
        }
    }

    private void loadProperties() {
        this.properties = new Properties();
        final String fileName = "technicalConfigurations.properties";
        final File configFile = this.searchFileByName(fileName);

        if (configFile != null) {
            try (FileInputStream fis = new FileInputStream(configFile)) {
                this.properties.load(fis);
            } catch (final IOException exception) {
                throw new RuntimeException("Failed to load properties from " + fileName, exception);
            }
        } else {
            throw new RuntimeException("Configuration file " + fileName + " not found in project directory.");
        }
    }

    // Initialise WebDriver based on environment and browser
    public WebDriver initializeDriver() {
        this.loadProperties();
        final String env = this.properties.getProperty("env", "local");
        final String browser = this.properties.getProperty("browser", "chrome");

        if ("local".equalsIgnoreCase(env)) {
            return this.createLocalDriver(browser);
        } else if ("remote".equalsIgnoreCase(env)) {
            try {
                final URL hubUrl = new URL(this.properties.getProperty("hubUrl"));
                return this.createRemoteDriver(browser, hubUrl);
            } catch (final Exception exception) {
                throw new RuntimeException("Failed to initialize remote WebDriver. Check hub URL.", exception);
            }
        } else {
            throw new RuntimeException("Invalid environment specified in configuration: " + env);
        }
    }

    // Create a local WebDriver instance based on browser type
    private WebDriver createLocalDriver(final String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                final ChromeOptions chromeOptions = new ChromeOptions();
                final String chromeOpts = this.properties.getProperty("chromeOptions", "");
                if (!chromeOpts.isEmpty()) {
                    chromeOptions.addArguments(chromeOpts.split(","));
                }
                return new ChromeDriver(chromeOptions);
            case "firefox":
                final FirefoxOptions firefoxOptions = new FirefoxOptions();
                final String firefoxOpts = this.properties.getProperty("firefoxOptions", "");
                if (!firefoxOpts.isEmpty()) {
                    firefoxOptions.addArguments(firefoxOpts.split(","));
                }
                return new FirefoxDriver(firefoxOptions);
            case "safari":
                return new SafariDriver();
            case "edge":
                final EdgeOptions edgeOptions = new EdgeOptions();
                final String edgeOpts = properties.getProperty("edgeOptions", "");
                if (!edgeOpts.isEmpty()) {
                    edgeOptions.addArguments(edgeOpts.split(","));
                }
                return new EdgeDriver(edgeOptions);
            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }
    }

    // Create a remote WebDriver instance based on browser type
    private WebDriver createRemoteDriver(final String browser, final URL hubUrl) {
        switch (browser.toLowerCase()) {
            case "chrome":
                return new RemoteWebDriver(hubUrl, new ChromeOptions());
            case "firefox":
                return new RemoteWebDriver(hubUrl, new FirefoxOptions());
            case "edge":
                return new RemoteWebDriver(hubUrl, new EdgeOptions());
            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }
    }

    // Search for a file by its name and return it as a File object
    private File searchFileByName(final String fileName) {
        final Path projectDir = Paths.get(System.getProperty("user.dir"));

        try (final Stream<Path> files = Files.walk(projectDir)) {
            return files
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().equals(fileName))
                    .findFirst()
                    .map(Path::toFile)
                    .orElse(null);
        } catch (IOException e) {
            throw new RuntimeException("Error while searching for file: " + fileName, e);
        }
    }

    public void configureDriver(final WebDriver driver) {
        //final int implicitWait = Integer.parseInt(this.properties.getProperty("implicitWait", "10"));
        final int pageLoadTimeout = Integer.parseInt(this.properties.getProperty("pageLoadTimeout", "30"));
        final int scriptTimeout = Integer.parseInt(this.properties.getProperty("scriptTimeout", "20"));

        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        driver.manage().window().maximize();
        //driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(scriptTimeout));

        //driver.get(properties.getProperty("baseUrl", "https://www.example.com"));
    }
    public static WebDriver getDriver(){
        return BaseClass.driver;
    }
}