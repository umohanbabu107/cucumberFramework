package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;


public class CommonUtilities {

    public Person getPersonData() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(".\\resources\\testData\\employee.json");
        try {
            return objectMapper.readValue(file, Person.class);
        }
        catch(Exception e){

        }
        return null;
    }
}
