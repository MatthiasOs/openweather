package de.ossi.openweather;

import de.ossi.openweather.model.currentweather.Coord;
import de.ossi.openweather.model.currentweather.CurrentWeather;
import de.ossi.openweather.model.forecast.Forecast;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import static java.net.http.HttpResponse.BodyHandlers;

@Service
public class WeatherService {
    public static final String OPENWEATHER_ENV = "OPENWEATHER_API_KEY";
    private final HttpClient client;
    private final WeatherConverter converter;

    public WeatherService(HttpClient client, WeatherConverter converter) {
        this.client = client;
        this.converter = converter;
    }

    public CurrentWeather readCurrentWeather(Coord location) throws IOException, InterruptedException {
        HttpResponse<String> response = send(createUri(OpenWeatherEndpoint.WEATHER, location));
        return converter.convertCurrentWeather(response.body());
    }

    public Forecast readForecast(Coord location) throws IOException, InterruptedException {
        HttpResponse<String> response = send(createUri(OpenWeatherEndpoint.FORECAST, location));
        return converter.convertForecast(response.body());
    }

    private HttpResponse<String> send(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(uri).build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new IllegalStateException("Error with Status: " + response.statusCode() + "\nBody:\n" + response.body());
        }
        return response;
    }

    /**
     * api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
     *
     * @see <a href="https://openweathermap.org/current">OpenWeather</a>
     */
    private URI createUri(OpenWeatherEndpoint openWeatherEndpoint, Coord location) {
        String openweatherApiKey = Optional.ofNullable(System.getenv(OPENWEATHER_ENV))
                                           .orElseThrow(ApiKeyNotFoundException::new);
        String url = "http://api.openweathermap.org/data/2.5/" + openWeatherEndpoint + "?lat=" + location.latitude() + "&lon=" + location.longitude() + "&appid=" + openweatherApiKey;
        return URI.create(url);
    }

    private static class ApiKeyNotFoundException extends RuntimeException {
        ApiKeyNotFoundException() {
            super(String.format("API Key not found in the System Environment Variable \"%s\"", OPENWEATHER_ENV));
        }
    }

    private enum OpenWeatherEndpoint {
        WEATHER, FORECAST;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}
