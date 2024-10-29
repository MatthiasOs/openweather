package de.ossi.model;

import de.ossi.model.currentweather.CurrentWeather;

public class CurrentWeatherConverter extends WeatherConverter<CurrentWeather> {
    public CurrentWeatherConverter() {
        super(CurrentWeather.class);
    }
}
