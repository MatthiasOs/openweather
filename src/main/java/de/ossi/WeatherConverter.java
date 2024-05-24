package de.ossi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.ossi.json.LocalDateTimeDeserializer;
import de.ossi.model.CurrentWeather;

import java.time.LocalDateTime;

public class WeatherConverter {
    private final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                                               .setPrettyPrinting()
                                               .create();

    public CurrentWeather convert(String json) {
        return gson.fromJson(json, CurrentWeather.class);
    }
}
