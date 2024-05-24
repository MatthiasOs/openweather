package de.ossi;

import com.google.gson.Gson;

public class WeatherConverter {
    private final Gson gson = new Gson();

    public CurrentWeather convert(String json) {
        return gson.fromJson(json, CurrentWeather.class);
    }
}
