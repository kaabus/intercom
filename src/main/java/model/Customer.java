package model;

import java.util.Objects;

/**
 * <p>A Customer model class that overrides equal to in such a way
 * that name and userId are mandatory in order to use equalTo. Variables are named
 * as per the given file in order to be parsed by Gson</p>
 */
public class Customer {

    private Integer user_id;
    private String name;
    private String latitude;
    private String longitude;
    private Double greatCircularDistance;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Double getGreatCircularDistance() {
        return greatCircularDistance;
    }

    public void setGreatCircularDistance(Double greatCircularDistance) {
        this.greatCircularDistance = greatCircularDistance;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", greatCircularDistance=" + greatCircularDistance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return user_id == customer.user_id &&
                name.equals(customer.name) &&
                Objects.equals(latitude, customer.latitude) &&
                Objects.equals(longitude, customer.longitude) &&
                Objects.equals(greatCircularDistance, customer.greatCircularDistance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, name, latitude, longitude, greatCircularDistance);
    }
}
