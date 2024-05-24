import model.Coord;
import model.CurrentWeather;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;

import static org.assertj.core.api.Assertions.assertThat;

class HttpServiceIntegrationTest {

    HttpService service = new HttpService(HttpClient.newHttpClient());
    WeatherConverter converter = new WeatherConverter();

    @Test
    void shouldConvertHttpResponseToJson() throws Exception {
        String httpResponse = service.readCurrentWeather(Coord.NUERNBERG);
        assertThat(httpResponse).isNotEmpty();
        CurrentWeather currentWeather = converter.convert(httpResponse);
        assertThat(currentWeather)
                .hasNoNullFieldsOrProperties()
                .extracting("cityName", "coord.latitude", "coord.longitude")
                .containsExactly("Nuremberg", Coord.NUERNBERG.latitude(), Coord.NUERNBERG.longitude());
    }
}