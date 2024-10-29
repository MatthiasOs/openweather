package de.ossi.service;

import com.google.inject.Inject;
import de.ossi.model.WeatherConverter;
import de.ossi.model.currentweather.CurrentWeather;

import java.net.http.HttpClient;

public class CurrentWeatherService extends WeatherService<CurrentWeather> {
    @Inject
    public CurrentWeatherService(HttpClient client, WeatherConverter<CurrentWeather> converter) {
        super(client, converter, "weather");
    }
}
