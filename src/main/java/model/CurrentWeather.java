package model;

import com.google.gson.annotations.SerializedName;

public class CurrentWeather {
    Coord coord;
    Wind wind;
    @SerializedName("name")
    String cityName;
}
