package ir.aut.ceit.app.application;

import java.util.Objects;

class ForecastWeather extends BaseWeather {

    ForecastWeather(String cityName, String cityId, double lat, double lon, int numberOfDay) {
        super(cityName, cityId, lat, lon, numberOfDay);
    }

    String byCityName() {
        StringBuilder stringBuilder = new StringBuilder().append("http://api.openweathermap.org/data/2.5/forecast/daily?q=").append(getCityName()).append("&cnt=").append(getNumberOfDays()).append("&appid=861e2828def7846ad5c93572bae1ea7e");
        return String.valueOf(stringBuilder);
    }

    String byCityId() {
        StringBuilder stringBuilder = new StringBuilder().append("http://api.openweathermap.org/data/2.5/forecast/daily?id=").append(getCityId()).append("&cnt=").append(getNumberOfDays()).append("&appid=861e2828def7846ad5c93572bae1ea7e");
        return String.valueOf(stringBuilder);
    }

    String byGeographicCoordinates() {
        StringBuilder stringBuilder = new StringBuilder().append("http://api.openweathermap.org/data/2.5/forecast/daily?lat=").append(getLat()).append("&lon=").append(getLon()).append("&cnt=").append(getNumberOfDays()).append("&appid=861e2828def7846ad5c93572bae1ea7e");
        return String.valueOf(stringBuilder);
    }

    static String printWeatherForecast(StringBuilder resultSb) {
        String printInfile;
        String coordination = null;
        String description = null;
        String humidity = null;
        String minimumTemp = null;
        String name = null;
        for (int i = 0; i < resultSb.length(); i++) {
            if (Objects.equals(resultSb.substring(i, i + 4), "lon:")) {
                int j = i;
                while (!Objects.equals(resultSb.substring(j, j + 3), "day")) {
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
                break;
            }
            if (Objects.equals(resultSb.substring(i, i + 9), "humidity:")) {
                int j = i;
                while (!Objects.equals(resultSb.substring(j, j + 9), "pressure:")) {
                    j++;
                }
                humidity = resultSb.substring(i + 10, j - 1);
            }
            if (Objects.equals(resultSb.substring(i, i + 13), "minimum temp:")) {
                int j = i;
                while (!Objects.equals(resultSb.substring(j, j + 13), "maximum temp:")) {
                    j++;
                }
                minimumTemp = resultSb.substring(i + 14, j - 1);
            }
            if (Objects.equals(resultSb.substring(i, i + 5), "name:")) {
                int j = i;
                while (!Objects.equals(resultSb.substring(j, j + 4), "lon:")) {
                    j++;
                }
                name = resultSb.substring(i + 6, j - 1);
            }
        }
        printInfile = "=========================================================================================================\n     City    Humidity     Description          Minimum_temp                Coordination    \n=========================================================================================================\n    " + name + "      " + humidity + "        " + description + "            " + minimumTemp + "              " + coordination + "\n=========================================================================================================\n";
        System.out.println(printInfile);
        return printInfile;
    }

}
