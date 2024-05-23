import model.City;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WeatherConverterTest {
    WeatherConverter converter = new WeatherConverter();

    @Test
    void shouldConvertToCityFromString() {
        String json = """
                {
                  "city": {
                    "id": 3163858,
                    "name": "Zocca"
                  }}""";
        City city = converter.convert(json);
        Assertions.assertThat(city)
                  .extracting("name")
                  .isEqualTo("Zocca");
    }

}