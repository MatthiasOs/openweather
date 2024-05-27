package de.ossi;

import de.ossi.model.CurrentWeather;

public class CurrentWeatherConverter implements WeatherConverter<CurrentWeather> {
    @Override
    public CurrentWeather convert(String json) {
        return gson.fromJson(json, CurrentWeather.class);
    }
}
