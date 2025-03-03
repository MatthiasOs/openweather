package de.ossi.openweather;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.ossi.openweather.json.LocalDateTimeDeserializer;
import de.ossi.openweather.model.currentweather.CurrentWeather;
import de.ossi.openweather.model.forecast.Forecast;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WeatherConverter {
    private final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                                               .setPrettyPrinting()
                                               .create();

    public CurrentWeather convertCurrentWeather(String json) {
        return gson.fromJson(json, CurrentWeather.class);
    }

    public Forecast convertForecast(String json) {
        return gson.fromJson(json, Forecast.class);
    }
}
