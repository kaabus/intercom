package services;

import com.google.gson.Gson;
import model.Customer;
import org.apache.commons.cli.*;

import java.io.*;
import java.util.ArrayList;

/**
 * <p>This class takes care of all
 * the input and output initial setup
 * and operations such as write and read.</p>
 */
public class IOSetup {

    private String inputFilePath;
    private String outputFilePath;
    private CommandLineParser parser;
    private HelpFormatter helpFormatter;
    private Options options;

    public IOSetup(CommandLineParser parser, HelpFormatter formatter, Options options) {
        this.parser = parser;
        this.helpFormatter = formatter;
        this.options = options;
    }

    /**
     * To setup the cli with the required options.
     *
     * @param args the args passed by the user
     * @return a list of all the customers as read from the input file given by the user
     * @throws IOException    when the file is not read properly
     * @throws ParseException when the arguments are incorrect
     */
    public ArrayList<Customer> setUpCLI(String[] args) throws IOException, ParseException {
        CommandLine cmd = parser.parse(options, args);
        if (cmd != null && cmd.hasOption("-i") && cmd.hasOption("-o")) {
            inputFilePath = cmd.getOptionValue("InputFile");
            outputFilePath = cmd.getOptionValue("OutputFile");
            return startReadFile();
        } else {
            helpFormatter.printHelp("Something went wrong", options);
            throw new UnrecognizedOptionException("Invalid Exception");
        }
    }

    /**
     * to start the read file operation
     *
     * @return array list of customers
     * @throws IOException in the event of wrong file or no file
     */
    public ArrayList<Customer> startReadFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
        return readFile(reader);
    }

    /**
     * Reads the file, if the content inside the file is correct,
     * a list of customer is created using the file data
     *
     * @param reader to read the desired file from thw give input
     * @return array list of customers
     * @throws IOException in the event of wrong file or no file
     */
    public ArrayList<Customer> readFile(BufferedReader reader) throws IOException {
        ArrayList<Customer> customers = new ArrayList<>();
        String customerFromFile = null;
        while ((customerFromFile = reader.readLine()) != null) {
            Gson gson = new Gson();
            Customer customer = gson.fromJson(customerFromFile, Customer.class);
            customers.add(customer);
        }
        reader.close();
        return customers;
    }

    /**
     * To prepare for writing a file by intialising required Classes
     *
     * @param customers a list of customers that should be printed out
     * @throws IOException in an event such as wrong file or no file
     */
    public void startWriteOutputFile(ArrayList<Customer> customers) throws IOException {
        FileOutputStream fos = new FileOutputStream(getOutputFilePath(outputFilePath));
        DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(fos));
        writeOutputFile(customers, outputStream);
    }

    /**
     * To get the output file path where the file will be written. The user may give the file location or file name.
     * In either event there will be a output file.
     *
     * @param filePath as given by the user
     * @return the filepath the output file will be stored
     */
    public String getOutputFilePath(String filePath) {
        if (filePath.contains(".txt")) {
            return filePath;
        } else {
            return filePath + "output.txt";
        }
    }

    /**
     * To write the output on a given output file path
     *
     * @param customers to be printed in a file
     * @param outStream to perform the operations
     * @throws IOException when there is any error while writing
     */
    public void writeOutputFile(ArrayList<Customer> customers, DataOutputStream outStream) throws IOException {

        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            outStream.writeChars(convertObjectToJson(c) + "\n");
        }
        outStream.close();
        startCheckIfOperationSuccess();
    }

    /**
     * Creates a new object file and is used to check if the operation is success
     */
    public void startCheckIfOperationSuccess() {
        File file = new File(outputFilePath);
        System.out.println(checkIfOperationSuccess(file));
    }

    /**
     * To check if the operation was successful by checking if the output file exists.
     *
     * @param file outputfile
     * @return print message to be used to print in the console
     */
    public String checkIfOperationSuccess(File file) {
        String message;
        if (file.exists()) {
            message = "Success ! The path to the output is : /" + file.getAbsolutePath();
        } else {
            message = "Something went wrong, please try again";
            // Cannot use system.exit as the tests fail if used.
            //System.exit(1);
        }
        return message;
    }

    /**
     * To convert any given customer to json
     *
     * @param customer that needs to be converted to json
     * @return a json string of custome json object
     */
    public String convertObjectToJson(Customer customer) {
        return new Gson().toJson(customer);
    }
}
