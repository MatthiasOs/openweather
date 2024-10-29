package de.ossi.model;

import de.ossi.model.forecast.Forecast;

public class ForecastConverter extends WeatherConverter<Forecast> {
    public ForecastConverter() {
        super(Forecast.class);
    }
}
