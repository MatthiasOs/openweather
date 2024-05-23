import model.CurrentWeather;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class WeatherConverterTest {
    private final WeatherConverter converter = new WeatherConverter();
    private static String JSON_FROM_FILE;

    @BeforeAll
    static void readFile() throws Exception {
        JSON_FROM_FILE = Files.readString(Path.of("src/test/resources/example.json"));
    }

    @Test
    void shouldConvertFromJsonExample() {
        CurrentWeather currentWeather = converter.convert(JSON_FROM_FILE);
        assertThat(currentWeather)
                .hasNoNullFieldsOrProperties()
                .extracting("wind.speed", "wind.degrees", "wind.gust")
                .containsExactly(0.62, 349.0, 1.18);
    }
}