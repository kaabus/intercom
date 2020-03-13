package services;

import com.google.gson.Gson;
import model.Customer;
import org.apache.commons.cli.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class IOSetupTest {

    private IOSetup cut;
    private String testFilePath;
    @Mock
    private CommandLineParser parser;
    @Mock
    private HelpFormatter helpFormatter;
    @Mock
    private ArrayList<Customer> customers;
    @Mock
    private Options options;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        File file = new File("D:\\projects\\tests\\intercom\\src\\test\\resources\\customers.txt");
        testFilePath = file.getAbsolutePath();
        cut = spy(new IOSetup(parser, helpFormatter, options));

    }

    @After
    public void validate() {
        validateMockitoUsage();
    }

    @Test(expected = ParseException.class)
    public void shouldThrowParseExceptionForWrongArguments() throws Exception {

        String[] args = new String[1];
        args[0] = "-asd";
        ArrayList<Customer> output = cut.setUpCLI(args);
        verify(helpFormatter).printHelp(any(String.class), any(Options.class));
        assertNull(output);
    }


    @Test
    public void testSetupCLI() throws IOException, ParseException {
        String[] args = new String[4];
        args[0] = "-i";
        args[1] = testFilePath;
        args[2] = "-o";
        args[3] = "output.txt";
        CommandLine cmd = mock(CommandLine.class);
        given(parser.parse(any(), any())).willReturn(cmd);
        given(cmd.hasOption(args[0])).willReturn(true);
        given(cmd.hasOption(args[2])).willReturn(true);
        given(cmd.getOptionValue("InputFile")).willReturn(args[1]);
        given(cmd.getOptionValue("OutputFile")).willReturn(args[3]);

        ArrayList<Customer> output = cut.setUpCLI(args);
        verify(cut).startReadFile();
        assert (output != null);


    }

    @Test
    public void testReadFile() throws Exception {
        String customer = "{\"latitude\": \"52.986375\", \"user_id\": 12, \"name\": \"Christina McArdle\", \"longitude\": \"-6.043701\"}";
        ArrayList<Customer> expectedOutput = new ArrayList<>();
        Gson gson = new Gson();
        Customer customer1 = gson.fromJson(customer, Customer.class);
        expectedOutput.add(customer1);
        BufferedReader reader = mock(BufferedReader.class);
        given(reader.readLine()).willReturn(customer).willReturn(null);
        ArrayList<Customer> functionOutput = cut.readFile(reader);
        verify(reader).close();
        assertEquals(expectedOutput, functionOutput);
    }

    @Test
    public void testWriteOutputFile() throws Exception {

        DataOutputStream dataOutputStream = mock(DataOutputStream.class);

        doNothing().when(cut).startCheckIfOperationSuccess();
        cut.writeOutputFile(customers, dataOutputStream);
        // Cannot verify the below mentioned method as mockito does not support verifying final methods and writeChars is a final Method
        //verify(dataOutputStream).writeChars(anyString());
        verify(dataOutputStream).close();
        verify(cut).startCheckIfOperationSuccess();
    }

    @Test
    public void testOutputFilePathWithoutExt() {
        String inputPath = "test/";
        String expectedPath = "test/output.txt";
        assertEquals(expectedPath, cut.getOutputFilePath(inputPath));
    }


    @Test
    public void testOutputFilePathWithExt() {
        String inputPath = "test/output.txt";
        String expectedPath = "test/output.txt";
        assertEquals(expectedPath, cut.getOutputFilePath(inputPath));
    }

    @Test
    public void testCheckIfOperationSuccess() {
        File file = mock(File.class);
        String filePath = "test.txt";
        String expected = "Success ! The path to the output is : /" + filePath;
        given(file.exists()).willReturn(true);
        given(file.getAbsolutePath()).willReturn(filePath);
        String functionOutput = cut.checkIfOperationSuccess(file);
        assertEquals(expected, functionOutput);
    }

    @Test
    public void testCheckIfOperationFails() {
        File file = mock(File.class);
        String expected = "Something went wrong, please try again";
        given(file.exists()).willReturn(false);
        String functionOutput = cut.checkIfOperationSuccess(file);
        assertEquals(expected, functionOutput);
    }
}