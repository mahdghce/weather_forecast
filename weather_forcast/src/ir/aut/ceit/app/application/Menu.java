package ir.aut.ceit.app.application;

import ir.aut.ceit.app.utility.OutputFileWriter;
import org.json.JSONException;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.exit;

public class Menu {
    public void menu() throws JSONException, IOException {
        System.out.println("Hello dear!");
        System.out.println("Welcome to my weather forecasting application :-)");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("If u want to exit enter Quit");
            System.out.println("Do u want 1.current.weather or 2.forecast.for.several.days ?");
            int answer1 = scanner.nextInt();
            if (answer1 == 1) {
                System.out.println("search city by 1.name 2.id 3.geographic.coordinates ?");
                int answer2 = scanner.nextInt();
                if (answer2 == 1) {
                    System.out.println("Please enter the name of the city:");
                    String cityName = scanner.next();
                    if (Objects.equals(cityName, "quit")) {
                        exit(0);
                    } else {
                        CurrentWeather currentWeather = new CurrentWeather("", "", 0, 0, 0);
                        OutputFileWriter outputFileWriter = new OutputFileWriter();
                        ApiWeather apiWeather = new ApiWeather();
                        currentWeather.setCityName(cityName);
                        currentWeather.printWeatherForOneCity(apiWeather.ParseResultOneDay(apiWeather.weather(currentWeather.byCityName())));
                        outputFileWriter.writeInFile(currentWeather.printInFile);
                    }
                }
                if (answer2 == 2) {
                    System.out.println("Pleas enter the id:");
                    String id = scanner.next();
                    if (Objects.equals(id, "quit")) {
                        exit(0);
                    } else {
                        CurrentWeather currentWeather = new CurrentWeather("", "", 0, 0, 0);
                        OutputFileWriter outputFileWriter = new OutputFileWriter();
                        ApiWeather apiWeather = new ApiWeather();
                        currentWeather.setCityId(id);
                        currentWeather.printWeatherForOneCity(apiWeather.ParseResultOneDay(apiWeather.weather(currentWeather.byCityId())));
                        outputFileWriter.writeInFile(currentWeather.printInFile);
                    }
                }
                if (answer2 == 3) {
                    System.out.println("Enter lon:");
                    int lon = scanner.nextInt();
                    System.out.println("Enter lat");
                    int lat = scanner.nextInt();
                    CurrentWeather currentWeather = new CurrentWeather("", "", 0, 0, 0);
                    OutputFileWriter outputFileWriter = new OutputFileWriter();
                    ApiWeather apiWeather = new ApiWeather();
                    currentWeather.setLat(lat);
                    currentWeather.setLon(lon);
                    currentWeather.printWeatherForOneCity(apiWeather.ParseResultOneDay(apiWeather.weather(currentWeather.byGeographicCoordinates())));
                    outputFileWriter.writeInFile(currentWeather.printInFile);
                }
            } else if (answer1 == 2) {
                System.out.println("search city by 1.name 2.id 3.geographic.coordinates ?");
                int answer3 = scanner.nextInt();
                if (answer3 == 1) {
                    System.out.println("Please enter the name of the city:");
                    String cityName = scanner.next();
                    if (Objects.equals(cityName, "quit")) {
                        exit(0);
                    } else {
                        ForecastWeather forecastWeather = new ForecastWeather("", "", 0, 0, 0);
                        OutputFileWriter outputFileWriter = new OutputFileWriter();
                        ApiWeather apiWeather = new ApiWeather();
                        forecastWeather.setCityName(cityName);
                        apiWeather.ParseResultSeveralDay(apiWeather.weather(forecastWeather.byCityName()));
                        outputFileWriter.writeInFile(String.valueOf(apiWeather.printInFile));
                    }
                }
                if (answer3 == 2) {
                    System.out.println("Pleas enter the id:");
                    String id = scanner.next();
                    if (Objects.equals(id, "quit")) {
                        exit(0);
                    } else {
                        ForecastWeather forecastWeather = new ForecastWeather("", "", 0, 0, 0);
                        OutputFileWriter outputFileWriter = new OutputFileWriter();
                        ApiWeather apiWeather = new ApiWeather();
                        forecastWeather.setCityId(id);
                        apiWeather.ParseResultSeveralDay(apiWeather.weather(forecastWeather.byCityId()));
                        outputFileWriter.writeInFile(String.valueOf(apiWeather.printInFile));
                    }
                }
                if (answer3 == 3) {
                    System.out.println("Enter lon:");
                    int lon = scanner.nextInt();
                    System.out.println("Enter lat");
                    int lat = scanner.nextInt();
                    ForecastWeather forecastWeather = new ForecastWeather("", "", 0, 0, 0);
                    OutputFileWriter outputFileWriter = new OutputFileWriter();
                    ApiWeather apiWeather = new ApiWeather();
                    forecastWeather.setLat(lat);
                    forecastWeather.setLon(lon);
                    apiWeather.ParseResultSeveralDay(apiWeather.weather(forecastWeather.byGeographicCoordinates()));
                    outputFileWriter.writeInFile(String.valueOf(apiWeather.printInFile));
                }
            } else {
                System.out.println("Wrong input!");
                exit(0);
            }
        }

    }
}
