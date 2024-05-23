import model.WeatherForecast;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class WeatherConverterTest {
    WeatherConverter converter = new WeatherConverter();
    private static String JSON_FROM_FILE;

    @BeforeAll
    static void readFile() throws Exception {
        JSON_FROM_FILE = Files.readString(Path.of("src/test/resources/example.json"));
    }

    @Test
    void coordShouldBeConverted() {
        WeatherForecast weatherForecast = converter.convert(JSON_FROM_FILE);
        assertThat(weatherForecast)
                .extracting("city.coord.longitude", "city.coord.latitude")
                .containsExactly(10.99, 44.34);
    }

    @Test
    void shouldConvertToPOJOFromJsonExample() throws Exception {
        WeatherForecast weatherForecast = converter.convert(JSON_FROM_FILE);
        assertThat(weatherForecast)
                .hasNoNullFieldsOrProperties()
                .extracting("city.name")
                .isEqualTo("Zocca");
    }

}