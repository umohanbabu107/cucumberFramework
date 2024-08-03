package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"},
        glue = {"stepDefinitions", "base"},
        plugin = {
                "pretty",
                "html:reports/report.html"
        },monochrome = true)
public class TestRunner {
    @AfterClass
    public static void renameReport(){
        String timestamp = getTimestamp();
        try{
        Files.move(Paths.get("reports"),
                Paths.get("reports/report"+timestamp+".html")

        );}
        catch (Exception exception){

        }
    }
    public static String getTimestamp(){
        return new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
    }
}
