package de.ossi;

import com.google.inject.Inject;
import de.ossi.model.Coord;
import de.ossi.model.Root;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpResponse.BodyHandlers;

public class OpenWeatherService<T extends Root> implements WeatherService<T> {

    private final HttpClient client;
    private final WeatherConverter<T> converter;

    @Inject
    public OpenWeatherService(HttpClient client, WeatherConverter<T> converter) {
        this.client = client;
        this.converter = converter;
    }

    public T readWeather(OpenWeatherEndpoint endpoint, Coord location) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(createUri(endpoint, location)).build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new IllegalStateException("Error with Status: " + response.statusCode() + "\nBody:\n" + response.body());
        }
        return converter.convert(response.body());
    }
}
