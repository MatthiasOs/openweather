import model.Coord;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import static java.net.http.HttpResponse.BodyHandlers;

public class HttpService {
    HttpClient client = HttpClient.newHttpClient();

    public String readForecast(Coord location) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(createUri(location)).build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new IllegalStateException("Error with Status: " + response.statusCode() + "\nBody" + response.body());
        }
        return response.body();
    }

    /**
     * api.openweathermap.org/data/2.5/forecast/daily?lat={lat}&lon={lon}&cnt={cnt}&appid={API key}
     *
     * @see <a href="https://openweathermap.org/forecast16">OpenWeather</a>
     */
    private URI createUri(Coord location) {
        String openweatherApiKey = Optional.ofNullable(System.getenv("OPENWEATHER_API_KEY"))
                                           .orElseThrow(ApiKeyNotFoundException::new);
        return URI.create("http://api.openweathermap.org/data/2.5/forecast/daily?lat=" + location.getLatitude() + "&lon=" + location.getLongitude() + "cnt=1&appid=" + openweatherApiKey);
    }

    public static class ApiKeyNotFoundException extends RuntimeException {
        ApiKeyNotFoundException() {
            super("API Key not found in the System Environment Variable OPENWEATHER_API_KEY");
        }
    }
}
