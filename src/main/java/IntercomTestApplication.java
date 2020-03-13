import factoryHelpers.ApplicationFactory;
import org.apache.commons.cli.ParseException;

import java.io.IOException;

/**
 * <p>This is the main class which should be run in order to start the application.
 * This is a cli application so to run this application a jar should be run by using
 * java -jar intercom-1.0-SNAPSHOT.jar -i someinputfile.txt -o someoutputfile.txt</p>
 */
public class IntercomTestApplication {


    public static void main(String[] args) throws IOException, ParseException {
        ApplicationFactory applicationFactory = new ApplicationFactory();
        applicationFactory.getProcessCustomers().processCustomers(args);

    }


}
