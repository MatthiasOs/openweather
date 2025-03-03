package de.ossi.openweather.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        //Timestamp is in Seconds
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(json.getAsJsonPrimitive()
                                                                 .getAsLong()), TimeZone.getDefault()
                                                                                        .toZoneId());
    }
}
