package services;

import model.Customer;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class OperationTest {

    private Operation cut;
    private String degreeToRadianExpX;
    private String absDeltaExp;
    private String deltaSigmaExp;
    private String gcdExp;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        cut = spy(new Operation());
        degreeToRadianExpX = "x*(pi/180)";
        String degreeToRadianExpY = "y*(pi/180)";
        absDeltaExp = "abs(" + degreeToRadianExpX + "-" + degreeToRadianExpY + ")";

        deltaSigmaExp = "acos(sin(x*(pi/180)) * sin(z*(pi/180)" + ")" +
                "+ (cos(" + "x*(pi/180)" + ") * cos(" + "z*(pi/180))" + ")" +
                " * cos(" + "abs(" + "w*(pi/180)" + "-" + "y*(pi/180)" + ")" + "))";
        int earthRadius = 6371;
        gcdExp = earthRadius + "* (" + deltaSigmaExp + ")";
    }

    @Test
    public void testConvertDegreeToRadian() {
        double result = new ExpressionBuilder(degreeToRadianExpX)
                .variable("x")
                .build()
                .setVariable("x", 53.339428)
                .evaluate();
        double output = cut.convertDegreeToRadian(53.339428);

        assert result == output;
    }

    @Test
    public void testGetAbsoluteDeltas() {
        double inputX = 53.339428;
        double inputY = -6.257664;
        double expected = new ExpressionBuilder(absDeltaExp)
                .variable("x")
                .variable("y")
                .build()
                .setVariable("x", inputX)
                .setVariable("y", inputY)
                .evaluate();
        double output = cut.calculateAbsoluteDeltas(inputX, inputY);

        assert expected == output;
    }

    @Test
    public void testGreatCircleDistance() {
        double intercomLat = 53.339428;
        double intercomLong = -6.257664;
        double custLong = -6.043701;
        double custLat = 52.986375;
        double expected = new ExpressionBuilder(gcdExp)
                .variable("w")
                .variable("x")
                .variable("y")
                .variable("z")
                .build()
                .setVariable("w", custLong)
                .setVariable("x", custLat)
                .setVariable("y", intercomLong)
                .setVariable("z", intercomLat)
                .evaluate();
        Customer customer = new Customer();
        customer.setLatitude("52.986375");
        customer.setLongitude("-6.043701");
        double output = cut.calculateGreatCircleDistance(customer);

        assert expected == output;


    }

    @Test
    public void testCalculateDeltaSigma() {
        double intercomLat = 53.339428;
        double intercomLong = -6.257664;
        double custLong = -6.043701;
        double custLat = 52.986375;
        double expected = new ExpressionBuilder(deltaSigmaExp)
                .variable("w")
                .variable("x")
                .variable("y")
                .variable("z")
                .build()
                .setVariable("w", custLong)
                .setVariable("x", custLat)
                .setVariable("y", intercomLong)
                .setVariable("z", intercomLat)
                .evaluate();
        Customer customer = new Customer();
        customer.setLatitude("52.986375");
        customer.setLongitude("-6.043701");
        double output = cut.calculateDeltaSigma(customer);

        assert expected == output;
    }
}