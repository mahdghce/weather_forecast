package ir.aut.ceit.app.application;

public class BaseWeather {
    private String cityName;
    private String cityId;
    private double lat;
    private double lon;
    private int numberOfDays;

    public BaseWeather(String cityName, String cityId, double lat, double lon, int numberOfDays) {
        this.cityName = cityName;
        this.cityId = cityId;
        this.lat = lat;
        this.lon = lon;
        this.numberOfDays = numberOfDays;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
}
