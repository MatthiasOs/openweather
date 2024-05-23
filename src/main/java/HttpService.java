import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import static java.net.http.HttpResponse.BodyHandlers;

public class HttpService {
    HttpClient client = HttpClient.newHttpClient();

    public String readForecast() throws IOException, InterruptedException {
        // api.openweathermap.org/data/2.5/forecast/daily?lat={lat}&lon={lon}&cnt={cnt}&appid={API key}
        HttpRequest request = HttpRequest.newBuilder(createUri(Location.NUERNBERG)).build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new IllegalStateException("Error with Status: " + response.statusCode() + "\nBody" + response.body());
        }
        return response.body();
    }

    private URI createUri(Location location) {
        String openweatherApiKey = Optional.ofNullable(System.getenv("OPENWEATHER_API_KEY"))
                                           .orElseThrow(ApiKeyNotFoundException::new);
        return URI.create("http://api.openweathermap.org/data/2.5/forecast/daily?lat=" + location.latitude + "&lon=" + location.longitude + "cnt=1&appid=" + openweatherApiKey);
    }

    public class ApiKeyNotFoundException extends RuntimeException {
        ApiKeyNotFoundException() {
            super("API Key not found in the System Environment Variable OPENWEATHER_API_KEY");
        }
    }
}
