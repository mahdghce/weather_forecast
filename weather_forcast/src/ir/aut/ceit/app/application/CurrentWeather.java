package ir.aut.ceit.app.application;

import java.util.Objects;

class CurrentWeather extends BaseWeather {

    CurrentWeather(String cityName, String cityId, double lat, double lon, int numberOfDay) {
        super(cityName, cityId, lat, lon, numberOfDay);
    }

    String printInFile;


    String byCityName() {
        StringBuilder stringBuilder = new StringBuilder().append("http://api.openweathermap.org/data/2.5/weather?q=").append(getCityName()).append("&appid=861e2828def7846ad5c93572bae1ea7e");
        return String.valueOf(stringBuilder);
    }

    String byCityId() {
        StringBuilder stringBuilder = new StringBuilder().append("http://api.openweathermap.org/data/2.5/weather?id=").append(getCityId()).append("&appid=861e2828def7846ad5c93572bae1ea7e");
        return String.valueOf(stringBuilder);
    }

    String byGeographicCoordinates() {
        StringBuilder stringBuilder = new StringBuilder().append("http://api.openweathermap.org/data/2.5/weather?lat=").append(getLat()).append("&lon=").append(getLon()).append("&appid=861e2828def7846ad5c93572bae1ea7e");
        return String.valueOf(stringBuilder);
    }

    void printWeatherForOneCity(String result) {
        StringBuilder resultSb = new StringBuilder().append(result);
        String coordination = null;
        String description = null;
        String humidity = null;
        String wind = null;
        String name = null;
        for (int i = 0; i < resultSb.length(); i++) {
            if (Objects.equals(resultSb.substring(i, i + 4), "lon:")) {
                int j = i;
                while (!Objects.equals(resultSb.substring(j, j + 3), "sys")) {
                    j++;
                }
                coordination = resultSb.substring(i, j - 1);
            }
            if (Objects.equals(resultSb.substring(i, i + 12), "description:")) {
                int j = i;
                while (!Objects.equals(resultSb.substring(j, j + 5), "icon:")) {
                    j++;
                }
                description = resultSb.substring(i + 13, j - 1);
            }
            if (Objects.equals(resultSb.substring(i, i + 9), "humidity:")) {
                int j = i;
                while (!Objects.equals(resultSb.substring(j, j + 9), "pressure:")) {
                    j++;
                }
                humidity = resultSb.substring(i + 10, j - 1);
            }
            if (Objects.equals(resultSb.substring(i, i + 4), "wind")) {
                int j = i;
                while (!Objects.equals(resultSb.substring(j, j + 6), "clouds")) {
                    j++;
                }
                wind = resultSb.substring(i + 5, j - 1);
            }
            if (Objects.equals(resultSb.substring(i, i + 5), "name:")) {
                int j = i;
                while (!Objects.equals(resultSb.substring(j, j + 4), "cod:")) {
                    j++;
                }
                name = resultSb.substring(i + 6, j - 1);
                break;
            }
        }
        System.out.println(printInFile = "=========================================================================================================\n    City    Humidity       Description           Wind-info                Coordination    \n=========================================================================================================\n   " + name + "     " + humidity + "          " + description + "       " + wind + "     " + coordination + "\n=========================================================================================================");
    }
}
