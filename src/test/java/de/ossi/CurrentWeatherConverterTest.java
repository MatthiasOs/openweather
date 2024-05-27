package de.ossi;

import de.ossi.model.CurrentWeather;
import de.ossi.model.Weather;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.list;

class CurrentWeatherConverterTest {
    private final WeatherConverter<CurrentWeather> converter = new CurrentWeatherConverter();
    private static String JSON_FROM_FILE;

    @BeforeAll
    static void readFile() throws Exception {
        JSON_FROM_FILE = Files.readString(Path.of("src/test/resources/currentweather.json"));
    }

    @Test
    void shouldHaveNoNullFieldsAfterConverting() {
        CurrentWeather currentWeather = converter.convert(JSON_FROM_FILE);
        assertThat(currentWeather)
                .hasNoNullFieldsOrProperties();
    }

    @Test
    void shouldConvertWind() {
        CurrentWeather currentWeather = converter.convert(JSON_FROM_FILE);
        assertThat(currentWeather)
                .extracting("wind.speed", "wind.degrees", "wind.gust")
                .containsExactly(0.62, 349.0, 1.18);
    }

    @Test
    void shouldConvertTemp() {
        CurrentWeather currentWeather = converter.convert(JSON_FROM_FILE);
        assertThat(currentWeather)
                .extracting("main.temp", "main.feelsLike")
                .containsExactly(25.33, 25.59);
    }

    @Test
    void shouldConvertSysFrom() {
        LocalDateTime sunrise = LocalDateTime.of(2022, 8, 30, 6, 36, 27);
        LocalDateTime sunset = LocalDateTime.of(2022, 8, 30, 19, 57, 28);
        CurrentWeather currentWeather = converter.convert(JSON_FROM_FILE);
        assertThat(currentWeather)
                .extracting("sys.country", "sys.sunrise", "sys.sunset")
                .containsExactly("IT", sunrise, sunset);
    }

    @Test
    void shouldConvertWeathersFrom() {
        CurrentWeather currentWeather = converter.convert(JSON_FROM_FILE);
        assertThat(currentWeather)
                .extracting(CurrentWeather::weathers, list(Weather.class))
                .singleElement()
                .extracting(Weather::main, Weather::description)
                .containsExactly("Rain", "moderate rain");
    }
}