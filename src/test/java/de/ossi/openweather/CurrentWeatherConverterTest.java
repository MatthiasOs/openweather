package de.ossi.openweather;

import de.ossi.openweather.model.currentweather.CurrentWeather;
import de.ossi.openweather.model.currentweather.Weather;
import de.ossi.openweather.model.forecast.City;
import de.ossi.openweather.model.forecast.Forecast;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.list;

class CurrentWeatherConverterTest {
    private final WeatherConverter converter = new WeatherConverter();
    private static String CURRENT_WEATHER_JSON;
    private static String FORECAST_JSON;

    @BeforeAll
    static void readFile() throws Exception {
        CURRENT_WEATHER_JSON = Files.readString(Path.of("src/test/resources/currentweather.json"));
        FORECAST_JSON = Files.readString(Path.of("src/test/resources/forecast.json"));
    }

    @Nested
    class ForecastShould {
        @Test
        void haveNoNullFieldsAfterConverting() {
            Forecast forecast = converter.convertForecast(FORECAST_JSON);
            assertThat(forecast)
                    .hasNoNullFieldsOrProperties();
        }

        @Test
        void convertCity() {
            Forecast forecast = converter.convertForecast(FORECAST_JSON);
            assertThat(forecast)
                    .extracting(Forecast::city)
                    .extracting(City::country, City::name)
                    .containsExactly("DE", "Nuremberg");
        }
    }

    @Nested
    class CurrentWeatherShould {
        @Test
        void haveNoNullFieldsAfterConverting() {
            CurrentWeather currentWeather = converter.convertCurrentWeather(CURRENT_WEATHER_JSON);
            assertThat(currentWeather)
                    .hasNoNullFieldsOrProperties();
        }

        @Test
        void convertWind() {
            CurrentWeather currentWeather = converter.convertCurrentWeather(CURRENT_WEATHER_JSON);
            assertThat(currentWeather)
                    .extracting("wind.speed", "wind.degrees", "wind.gust")
                    .containsExactly(0.62, 349.0, 1.18);
        }

        @Test
        void convertTemp() {
            CurrentWeather currentWeather = converter.convertCurrentWeather(CURRENT_WEATHER_JSON);
            assertThat(currentWeather)
                    .extracting("main.temp", "main.feelsLike")
                    .containsExactly(25.33, 25.59);
        }

        @Test
        void convertSysFrom() {
            LocalDateTime sunrise = LocalDateTime.of(2022, 8, 30, 6, 36, 27);
            LocalDateTime sunset = LocalDateTime.of(2022, 8, 30, 19, 57, 28);
            CurrentWeather currentWeather = converter.convertCurrentWeather(CURRENT_WEATHER_JSON);
            assertThat(currentWeather)
                    .extracting("sys.country", "sys.sunrise", "sys.sunset")
                    .containsExactly("IT", sunrise, sunset);
        }

        @Test
        void convertWeathersFrom() {
            CurrentWeather currentWeather = converter.convertCurrentWeather(CURRENT_WEATHER_JSON);
            assertThat(currentWeather)
                    .extracting(CurrentWeather::weathers, list(Weather.class))
                    .singleElement()
                    .extracting(Weather::main, Weather::description)
                    .containsExactly("Rain", "moderate rain");
        }
    }
}