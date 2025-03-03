package de.ossi.openweather.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.math.BigDecimal;

public class KelvinToCelsiusConverter implements JsonDeserializer<Double> {
    private static final BigDecimal KELVIN_CONSTANT = new BigDecimal("273.15");

    @Override
    public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        //Use BigDecimal, because floating point numbers in java are not exact
        BigDecimal originalValue = json.getAsJsonPrimitive().getAsBigDecimal();
        return originalValue.subtract(KELVIN_CONSTANT).doubleValue();
    }
}
