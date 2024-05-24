package de.ossi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public record CurrentWeather(Coord coord, Wind wind, @SerializedName("name") String cityName, Sys sys, Main main,
                             @SerializedName("weather") List<Weather> weathers) {}
