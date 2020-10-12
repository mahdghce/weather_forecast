package ir.aut.ceit.app.application;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

class ApiWeather {
    StringBuilder printInFile = new StringBuilder();

    String weather(String url) {
        String result = "";
        try {
            URL urlWeather = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlWeather.openConnection();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 8192);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
            } else {
                System.out.println("Error in httpURLConnection.getResponseCode()!!!");
            }
        } catch (IOException ex) {
            Logger.getLogger(ApiWeather.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    String ParseResultOneDay(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);

        //"coord"
        JSONObject jsonObjectCoord = jsonObject.getJSONObject("coord");
        Double resultLon = jsonObjectCoord.getDouble("lon");
        Double resultLat = jsonObjectCoord.getDouble("lat");

        //"sys"
        JSONObject jsonObjectSys = jsonObject.getJSONObject("sys");
        String resultCountry = jsonObjectSys.getString("country");
        int resultSunrise = jsonObjectSys.getInt("sunrise");
        int resultSunset = jsonObjectSys.getInt("sunset");

        //"weather"
        String result_weather;
        JSONArray jsonArrayWeather = jsonObject.getJSONArray("weather");
        if (jsonArrayWeather.length() > 0) {
            JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
            int resultId = jsonObjectWeather.getInt("id");
            String resultMain = jsonObjectWeather.getString("main");
            String resultDescription = jsonObjectWeather.getString("description");
            String resultIcon = jsonObjectWeather.getString("icon");
            result_weather = " weather id: " + resultId + " main: " + resultMain + " description: " + resultDescription + " icon: " + resultIcon;
        } else {
            result_weather = "weather empty!";
        }

        //"base"
        String resultBase = jsonObject.getString("base");

        //"main"
        JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
        Double resultTemp = jsonObjectMain.getDouble("temp");
        Double resultPressure = jsonObjectMain.getDouble("pressure");
        Double resultHumidity = jsonObjectMain.getDouble("humidity");
        Double resultTempMin = jsonObjectMain.getDouble("temp_min");

        //"wind"
        JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");
        Double resultSpeed = jsonObjectWind.getDouble("speed");

        //Double resultGust = jsonObjectWind.getDouble("gust");
        Double resultDeg = jsonObjectWind.getDouble("deg");
        String resultWind = " wind speed: " + resultSpeed + " deg: " + resultDeg;

        //"clouds"
        JSONObject jsonObjectClouds = jsonObject.getJSONObject("clouds");
        int resultAll = jsonObjectClouds.getInt("all");

        //"dt"
        int resultDt = jsonObject.getInt("dt");

        //"id"
        int resultId = jsonObject.getInt("id");

        //"name"
        String resultName = jsonObject.getString("name");

        //"cod"
        int resultCod = jsonObject.getInt("cod");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("coord lon: ").append(resultLon).append(" lat: ").append(resultLat).append(" sys country: ").append(resultCountry).append(" sunrise: ").append(resultSunrise).append(" sunset: ").append(resultSunset).append(result_weather).append(" base: ").append(resultBase).append(" main temp: ").append(resultTemp).append(" humidity: ").append(resultHumidity).append(" pressure: ").append(resultPressure).append(" temp_min: ").append(resultTempMin).append(" temp_max: ").append(resultTempMin).append(resultWind).append(" clouds all: ").append(resultAll).append(" dt: ").append(resultDt).append(" id: ").append(resultId).append(" name: ").append(resultName).append(" cod: ").append(resultCod);
        return String.valueOf(stringBuilder);
    }

    void ParseResultSeveralDay(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);

        //"cod"
        int resultCod = jsonObject.getInt("cod");

        //"message"
        double resultMessage = jsonObject.getInt("message");

        //"city"
        JSONObject jsonObjectCity = jsonObject.getJSONObject("city");
        int resultCityId = jsonObjectCity.getInt("id");
        String resultName = jsonObjectCity.getString("name");

        //"coord"
        JSONObject jsonObjectCoord = jsonObjectCity.getJSONObject("coord");
        Double resultLon = jsonObjectCoord.getDouble("lon");
        Double resultLat = jsonObjectCoord.getDouble("lat");

        //"country"
        String resultCountry = jsonObjectCity.getString("country");

        //"cnt"
        int resultCnt = jsonObject.getInt("cnt");

        StringBuilder stringBuilder = new StringBuilder();

        //"list"
        JSONArray jsonArrayList = jsonObject.getJSONArray("list");
        for (int i = 0; i < jsonArrayList.length(); i++) {
            stringBuilder.append(" name: ").append(resultName).append(" lon: ").append(resultLon).append(" lat: ").append(resultLat);
            String result_list;
            if (jsonArrayList.length() > 0) {
                JSONObject jsonObjectList = jsonArrayList.getJSONObject(i);
                int resultDt = jsonObjectList.getInt("dt");
                JSONObject jsonObjectTemp = jsonObjectList.getJSONObject("temp");
                Double resultDay = jsonObjectTemp.getDouble("day");
                Double resultMin = jsonObjectTemp.getDouble("min");
                Double resultMax = jsonObjectTemp.getDouble("max");
                Double resultNight = jsonObjectTemp.getDouble("night");
                Double resultEve = jsonObjectTemp.getDouble("eve");
                Double resultMorn = jsonObjectTemp.getDouble("morn");
                double resultPressure = jsonObjectList.getInt("pressure");
                int resultHumidity = jsonObjectList.getInt("humidity");

                //"weather"
                String result_weather;
                JSONArray jsonArrayWeather = jsonObjectList.getJSONArray("weather");
                if (jsonArrayWeather.length() > 0) {
                    JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
                    int resultId = jsonObjectWeather.getInt("id");
                    String resultMain = jsonObjectWeather.getString("main");
                    String resultDescription = jsonObjectWeather.getString("description");
                    String resultIcon = jsonObjectWeather.getString("icon");
                    result_weather = " weather id: " + resultId + " main: " + resultMain + " description: " + resultDescription + " icon: " + resultIcon;
                } else {
                    result_weather = "weather empty!";
                }
                result_list = " day temp: " + resultDay + " minimum temp: " + resultMin + " maximum temp: " + resultMax + " night temp: " + resultNight + " evening temp: " + resultEve + " morning temp: " + resultMorn + " humidity: " + resultHumidity + " pressure: " + resultPressure + result_weather;
            } else {
                result_list = "List is empty!";
            }
            printInFile.append(ForecastWeather.printWeatherForecast(stringBuilder.append(result_list)));
            stringBuilder.delete(0, stringBuilder.length());
        }
    }
}
