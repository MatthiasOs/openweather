package de.ossi.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.ossi.json.LocalDateTimeDeserializer;
import de.ossi.model.currentweather.CurrentWeather;
import de.ossi.model.forecast.Forecast;

import java.time.LocalDateTime;

public abstract class WeatherConverter<T> {
    protected Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                                 .setPrettyPrinting()
                                 .create();
    private final Class<T> clazz;

    public WeatherConverter(Class<T> clazz){
        this.clazz=clazz;
    }

    public T convert(String json){
        return gson.fromJson(json, clazz);
    }
}
