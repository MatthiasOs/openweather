package de.ossi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.ossi.json.LocalDateTimeDeserializer;
import de.ossi.model.currentweather.CurrentWeather;
import de.ossi.model.forecast.Forecast;

import java.time.LocalDateTime;

public class WeatherConverter {
    Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                                 .setPrettyPrinting()
                                 .create();

    public CurrentWeather convertCurrentWeather(String json) {
        return gson.fromJson(json, CurrentWeather.class);
    }

    public Forecast convertForecast(String json) {
        return gson.fromJson(json, Forecast.class);
    }
}
