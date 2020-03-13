package services;

import model.Customer;
import org.apache.commons.cli.ParseException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class behaves like a controller between IOSetup and Operation
 */
public class ProcessCustomers {
    private IOSetup ioSetup;
    private Operation operation;

    public ProcessCustomers(IOSetup ioSetup, Operation operation) {
        this.ioSetup = ioSetup;
        this.operation = operation;
    }

    /**
     * To process the args passed by the user and get the customers list
     *
     * @param args by the user
     * @throws IOException    if the input or output is wrong
     * @throws ParseException if the args are wrong
     */
    public void processCustomers(String[] args) throws IOException, ParseException {
        ArrayList<Customer> customers = ioSetup.setUpCLI(args);

        if (customers != null && !customers.isEmpty()) {
            for (Customer c : customers) {
                c.setGreatCircularDistance(operation.calculateGreatCircleDistance(c));
            }
            ioSetup.startWriteOutputFile(computeClosestCustomers(customers));
        }

    }

    /**
     * Computes the customer's gcd in respect to intercom's location
     *
     * @param customers with lat and long
     * @return customer list to be printed
     * @throws IOException
     */
    public ArrayList<Customer> computeClosestCustomers(ArrayList<Customer> customers) throws IOException {
        ArrayList<Customer> customersWithinRange = new ArrayList<>();
        for (Customer c : customers) {
            if (c.getGreatCircularDistance() <= 100) {
                Customer customer = new Customer();
                customer.setName(c.getName());
                customer.setUser_id(c.getUser_id());
                customersWithinRange.add(customer);
            }
        }
        return customersWithinRange;
    }
}
