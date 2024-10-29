package de.ossi.service;

import com.google.inject.Inject;
import de.ossi.model.WeatherConverter;
import de.ossi.model.forecast.Forecast;

import java.net.http.HttpClient;

public class ForecastService extends WeatherService<Forecast> {

    @Inject
    public ForecastService(HttpClient client, WeatherConverter<Forecast> converter) {
        super(client, converter, "forecast");
    }
}
