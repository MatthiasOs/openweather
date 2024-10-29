package de.ossi.model;

import de.ossi.model.forecast.City;
import de.ossi.model.forecast.Forecast;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class ForecastConverterTest {
    private final WeatherConverter<Forecast> forecastWeatherConverter = new ForecastConverter();
    private static String FORECAST_JSON;

    @BeforeAll
    static void readFile() throws Exception {
        FORECAST_JSON = Files.readString(Path.of("src/test/resources/forecast.json"));
    }

    @Test
    void forecastShouldHaveNoNullFieldsAfterConverting() {
        Forecast forecast = forecastWeatherConverter.convert(FORECAST_JSON);
        assertThat(forecast)
                .hasNoNullFieldsOrProperties();
    }

    @Test
    void forecastShouldConvertCity() {
        Forecast forecast = forecastWeatherConverter.convert(FORECAST_JSON);
        assertThat(forecast)
                .extracting(Forecast::city)
                .extracting(City::country, City::name)
                .containsExactly("DE", "Nuremberg");
    }
}
