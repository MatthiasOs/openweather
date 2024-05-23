import model.Coord;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HttpServiceIntegrationTest {

    HttpService service = new HttpService();

    @Test
    void shouldGetHttpResponse() throws Exception {
        String httpResponse = service.readForecast(Coord.NUERNBERG);
        Assertions.assertThat(httpResponse).isNotEmpty();
    }
}