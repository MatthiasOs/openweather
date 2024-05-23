package model;

import com.google.gson.annotations.SerializedName;

public record CurrentWeather(Coord coord, Wind wind, @SerializedName("name") String cityName) {}
