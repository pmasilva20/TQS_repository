package ua.tqs.hw1.Datamodel;

import javax.persistence.*;
import java.util.Objects;

public class AirQuality {

    private String country;
    private String state;
    private String city;
    private Coordinates coordinates;
    private int air_quality;
    private double co;
    private double no;
    private double no2;
    private double o3;
    private double so2;
    private double pm2_5;
    private double pm10;
    private double nh3;
    private long timestamp_request;
    private long date;


    public AirQuality(String city, int air_quality, long timestamp_request,long date) {
        this.city = city;
        this.air_quality = air_quality;
        this.timestamp_request = timestamp_request;
        this.date = date;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public long getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAir_quality(int air_quality) {
        this.air_quality = air_quality;
    }

    public void setCo(double co) {
        this.co = co;
    }

    public void setNo(double no) {
        this.no = no;
    }

    public void setNo2(double no2) {
        this.no2 = no2;
    }

    public void setO3(double o3) {
        this.o3 = o3;
    }

    public void setSo2(double so2) {
        this.so2 = so2;
    }

    public void setPm2_5(double pm2_5) {
        this.pm2_5 = pm2_5;
    }

    public double getPm10() {
        return pm10;
    }

    public void setPm10(double pm10) {
        this.pm10 = pm10;
    }

    public void setNh3(double nh3) {
        this.nh3 = nh3;
    }

    public void setTimestamp_request(int timestamp_request) {
        this.timestamp_request = timestamp_request;
    }


    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public int getAir_quality() {
        return air_quality;
    }

    public double getCo() {
        return co;
    }

    public double getNo() {
        return no;
    }

    public double getNo2() {
        return no2;
    }

    public double getO3() {
        return o3;
    }

    public double getSo2() {
        return so2;
    }

    public double getPm2_5() {
        return pm2_5;
    }

    public double getNh3() {
        return nh3;
    }

    public long getTimestamp_request() {
        return timestamp_request;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirQuality that = (AirQuality) o;
        return date == that.date &&
                coordinates.equals(that.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, date);
    }

    @Override
    public String toString() {
        return "AirQuality{" +
                "country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", coordinates=" + coordinates +
                ", air_quality=" + air_quality +
                ", co=" + co +
                ", no=" + no +
                ", no2=" + no2 +
                ", o3=" + o3 +
                ", so2=" + so2 +
                ", pm2_5=" + pm2_5 +
                ", pm10=" + pm10 +
                ", nh3=" + nh3 +
                ", timestamp_request=" + timestamp_request +
                ", date=" + date +
                '}';
    }
}
