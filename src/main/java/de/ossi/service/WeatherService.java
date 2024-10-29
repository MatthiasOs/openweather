package de.ossi.service;

import com.google.inject.Inject;
import de.ossi.model.WeatherConverter;
import de.ossi.model.currentweather.Coord;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import static java.net.http.HttpResponse.BodyHandlers;

public abstract class WeatherService<T> {
    public static final String OPENWEATHER_ENV = "OPENWEATHER_API_KEY";
    private final HttpClient client;
    private final WeatherConverter<T> converter;
    private final String endpoint;

    @Inject
    public WeatherService(HttpClient client, WeatherConverter<T> converter, String endpoint) {
        this.client = client;
        this.converter = converter;
        this.endpoint = endpoint;
    }

    public T read(Coord location) throws IOException, InterruptedException {
        HttpResponse<String> response = send(createUri(location));
        return converter.convert(response.body());
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
    private URI createUri(Coord location) {
        String openweatherApiKey = Optional.ofNullable(System.getenv(OPENWEATHER_ENV))
                                           .orElseThrow(ApiKeyNotFoundException::new);
        String url = "http://api.openweathermap.org/data/2.5/" + endpoint + "?lat=" + location.latitude() + "&lon=" + location.longitude() + "&appid=" + openweatherApiKey;
        return URI.create(url);
    }

    private static class ApiKeyNotFoundException extends RuntimeException {
        ApiKeyNotFoundException() {
            super("API Key not found in the System Environment Variable OPENWEATHER_API_KEY");
        }
    }

    enum OpenWeatherEndpoint {
        WEATHER, FORECAST;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}
