package de.ossi;

import de.ossi.model.forecast.Forecast;

public class ForecastConverter implements WeatherConverter<Forecast> {
    @Override
    public Forecast convert(String json) {
        return gson.fromJson(json, Forecast.class);
    }
}
