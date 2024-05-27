package de.ossi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.ossi.json.LocalDateTimeDeserializer;
import de.ossi.model.Root;

import java.time.LocalDateTime;

public interface WeatherConverter<T extends Root> {
    Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                                 .setPrettyPrinting()
                                 .create();

    T convert(String json);
}
