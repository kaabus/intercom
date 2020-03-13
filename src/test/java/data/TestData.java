package data;

import model.Customer;

import java.util.ArrayList;

public class TestData {

    private static final String TEST_USER_NAME = "Test_name";
    private static final String TEST_LATIDUDE = "1.234";
    private static final String TEST_LONGITUDE = "1.234";
    private static final int TEST_USERID = 1;
    private static final Double TEST_GCD_NEAR = 2.1234;
    private static final Double TEST_GCD_FAR = 100.1234;

    public static Customer createCompleteCustomer() {
        Customer customer = new Customer();
        customer.setName(TEST_USER_NAME);
        customer.setUser_id(TEST_USERID);
        customer.setLatitude(TEST_LATIDUDE);
        customer.setLongitude(TEST_LONGITUDE);
        customer.setGreatCircularDistance(TEST_GCD_NEAR);
        return customer;
    }

    public static Customer createInitialCustomer() {
        Customer customer = new Customer();
        customer.setName(TEST_USER_NAME);
        customer.setUser_id(TEST_USERID);
        customer.setLatitude(TEST_LATIDUDE);
        customer.setLongitude(TEST_LONGITUDE);
        return customer;
    }

    public static Customer createOutputCustomer() {
        Customer customer = new Customer();
        customer.setName(TEST_USER_NAME);
        customer.setUser_id(TEST_USERID);
        return customer;
    }

    public static ArrayList<Customer> createCompletedCustomerList() {
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(createCompleteCustomer());
        customers.add(createFarCustomer());
        return customers;
    }

    public static Customer createFarCustomer() {
        Customer customer = new Customer();
        customer.setName(TEST_USER_NAME);
        customer.setUser_id(TEST_USERID);
        customer.setLatitude(TEST_LATIDUDE);
        customer.setLongitude(TEST_LONGITUDE);
        customer.setGreatCircularDistance(TEST_GCD_FAR);
        return customer;
    }

    public static ArrayList<Customer> createOutputCustomerList() {
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(createOutputCustomer());
        return customers;
    }
}
