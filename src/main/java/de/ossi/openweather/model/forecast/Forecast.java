package de.ossi.openweather.model.forecast;

import com.google.gson.annotations.SerializedName;
import de.ossi.openweather.model.currentweather.CurrentWeather;

import java.util.List;

public record Forecast(City city, @SerializedName("list") List<CurrentWeather> weathers) {
}
