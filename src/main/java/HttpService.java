import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpResponse.BodyHandlers;

public class HttpService {
    //TODO auslagern in properties File?
    private static final String API_KEY = "9dd2762e6c641fd8911f758d7de85f29";
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
        return URI.create("http://api.openweathermap.org/data/2.5/forecast/daily?lat=" + location.latitude + "&lon=" + location.longitude + "cnt=1&appid=" + API_KEY);
    }
}
