package de.ossi;

import de.ossi.model.forecast.City;
import de.ossi.model.forecast.Forecast;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class ForecastConverterTest {
    private final WeatherConverter<Forecast> converter = new ForecastConverter();
    private static String JSON_FROM_FILE;

    @BeforeAll
    static void readFile() throws Exception {
        JSON_FROM_FILE = Files.readString(Path.of("src/test/resources/forecast.json"));
    }

    @Test
    void shouldHaveNoNullFieldsAfterConverting() {
        Forecast forecast = converter.convert(JSON_FROM_FILE);
        assertThat(forecast)
                .hasNoNullFieldsOrProperties();
    }

    @Test
    void shouldConvertCity() {
        Forecast forecast = converter.convert(JSON_FROM_FILE);
        assertThat(forecast)
                .extracting(Forecast::city)
                .extracting(City::country, City::name)
                .containsExactly("DE", "Nuremberg");
    }
}