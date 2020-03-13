package services;

import model.Customer;

/**
 * <p>This class handles all the mathematical formulas that are required
 * to solve the mystery of the distance between 2 locations</p>
 */
public class Operation {

    /**
     * Converts a degree to radian
     *
     * @param degreeValue which is to be converted to radian
     * @return converted radian value
     */
    public Double convertDegreeToRadian(Double degreeValue) {
        return degreeValue * (Math.PI / 180);
    }

    /**
     * get the absolute delta between 2 given values
     *
     * @param value1
     * @param value2
     * @return absolute delta
     */
    public Double calculateAbsoluteDeltas(Double value1, Double value2) {
        return Math.abs(convertDegreeToRadian(value1) - convertDegreeToRadian(value2));
    }

    /**
     * calculate the gcd of the given customer
     *
     * @param customer of which the gcd is to be calcuated with respect to the intercom location
     * @return great circle distance
     */
    public Double calculateGreatCircleDistance(Customer customer) {
        Double deltaSigma = calculateDeltaSigma(customer);
        int earthRadius = 6371;
        return earthRadius * deltaSigma;
    }

    /**
     * Calculate the delta sigma between customer's location and intercom's location
     *
     * @param customer
     * @return delta sigma
     */
    public Double calculateDeltaSigma(Customer customer) {
        Double customerLatitudeRadian = convertDegreeToRadian(Double.parseDouble(customer.getLatitude()));
        Double intercomLatitude = 53.339428;
        Double intercomLatitudeRadian = convertDegreeToRadian(intercomLatitude);
        Double intercomLongitude = -6.257664;
        Double deltaLambda = calculateAbsoluteDeltas(Double.parseDouble(customer.getLongitude()), intercomLongitude);

        return Math.acos((Math.sin(customerLatitudeRadian) * Math.sin(intercomLatitudeRadian))
                + (Math.cos(customerLatitudeRadian) * Math.cos(intercomLatitudeRadian) * Math.cos(deltaLambda)));
    }
}
