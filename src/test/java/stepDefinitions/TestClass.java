package stepDefinitions;

import org.openqa.selenium.chrome.ChromeDriver;
import utilities.CommonUtilities;
import utilities.Person;

public class TestClass {
    public static void main(String args[]){
        CommonUtilities commonUtilities = new CommonUtilities(new ChromeDriver());
        Person person = commonUtilities.getPersonData();
        System.out.println(person.getFirstName());
        System.out.println(person.getLastName());
        System.out.println(person.getAddress().get(0).getStreet());
        System.out.println(person.getAddress().get(0).getCountry());
        System.out.println(person.getAddress().get(1).getStreet());
        System.out.println(person.getAddress().get(1).getCountry());
    }
}
