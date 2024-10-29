package de.ossi.model;

import de.ossi.model.currentweather.CurrentWeather;
import de.ossi.model.currentweather.Weather;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.list;

class CurrentWeatherConverterTest {
    private final WeatherConverter<CurrentWeather> currentWeatherConverter = new CurrentWeatherConverter();
    private static String CURRENT_WEATHER_JSON;

    @BeforeAll
    static void readFile() throws Exception {
        CURRENT_WEATHER_JSON = Files.readString(Path.of("src/test/resources/currentweather.json"));
    }

    @Test
    void currentWeatherShouldHaveNoNullFieldsAfterConverting() {
        CurrentWeather currentWeather = currentWeatherConverter.convert(CURRENT_WEATHER_JSON);
        assertThat(currentWeather)
                .hasNoNullFieldsOrProperties();
    }

    @Test
    void currentWeatherShouldConvertWind() {
        CurrentWeather currentWeather = currentWeatherConverter.convert(CURRENT_WEATHER_JSON);
        assertThat(currentWeather)
                .extracting("wind.speed", "wind.degrees", "wind.gust")
                .containsExactly(0.62, 349.0, 1.18);
    }

    @Test
    void currentWeatherShouldConvertTemp() {
        CurrentWeather currentWeather = currentWeatherConverter.convert(CURRENT_WEATHER_JSON);
        assertThat(currentWeather)
                .extracting("main.temp", "main.feelsLike")
                .containsExactly(25.33, 25.59);
    }

    @Test
    void currentWeatherShouldConvertSysFrom() {
        LocalDateTime sunrise = LocalDateTime.of(2022, 8, 30, 6, 36, 27);
        LocalDateTime sunset = LocalDateTime.of(2022, 8, 30, 19, 57, 28);
        CurrentWeather currentWeather = currentWeatherConverter.convert(CURRENT_WEATHER_JSON);
        assertThat(currentWeather)
                .extracting("sys.country", "sys.sunrise", "sys.sunset")
                .containsExactly("IT", sunrise, sunset);
    }

    @Test
    void currentWeatherShouldConvertWeathersFrom() {
        CurrentWeather currentWeather = currentWeatherConverter.convert(CURRENT_WEATHER_JSON);
        assertThat(currentWeather)
                .extracting(CurrentWeather::weathers, list(Weather.class))
                .singleElement()
                .extracting(Weather::main, Weather::description)
                .containsExactly("Rain", "moderate rain");
    }
}