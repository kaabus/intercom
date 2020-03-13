package factoryHelpers;

import org.apache.commons.cli.*;
import services.IOSetup;
import services.Operation;
import services.ProcessCustomers;

/**
 * <p>The implementation of the ApplicationHelper that
 * returns the required instances</p>
 */
public class ApplicationHelperImpl implements ApplicationHelper {
    @Override
    public IOSetup getIOSetupInstance() {
        CommandLineParser parser = new DefaultParser();
        HelpFormatter helpFormatter = new HelpFormatter();
        Option inputOpt = new Option(
                "i",
                "InputFile",
                true,
                "An option to specify the path for an input file." +
                        " The input file must be in txt format");
        Option outputOpt = new Option(
                "o",
                "OutputFile",
                true,
                "An option to specify the path for output file.");
        Options options = new Options();
        options.addOption(inputOpt);
        options.addOption(outputOpt);
        return new IOSetup(parser, helpFormatter, options);
    }

    @Override
    public ProcessCustomers getProcessCustomersInstance() {
        IOSetup ioSetup = getIOSetupInstance();
        Operation operation = new Operation();
        return new ProcessCustomers(ioSetup, operation);
    }
}
