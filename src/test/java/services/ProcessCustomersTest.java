package services;

import data.TestData;
import model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ProcessCustomersTest {

    @Mock
    private IOSetup ioSetup;
    @Mock
    private Operation operation;

    private ProcessCustomers cut;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        cut = spy(new ProcessCustomers(ioSetup, operation));
    }

    @Test
    public void testProcessCustomers() throws Exception {
        ArrayList<Customer> input = TestData.createCompletedCustomerList();
        BDDMockito.given(ioSetup.setUpCLI(any())).willReturn(input);
        cut.processCustomers(any());
        verify(operation, times(2)).calculateGreatCircleDistance(input.get(0));
        verify(cut).computeClosestCustomers(input);
        verify(ioSetup).startWriteOutputFile(any());

    }

    @Test
    public void testComputeClosestCustomers() throws Exception {
        ArrayList<Customer> expectedOutput = TestData.createOutputCustomerList();
        ArrayList<Customer> input = TestData.createCompletedCustomerList();
        ArrayList<Customer> output = cut.computeClosestCustomers(input);
        assertEquals(expectedOutput, output);
    }
}