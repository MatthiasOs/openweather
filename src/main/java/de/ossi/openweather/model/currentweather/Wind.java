package de.ossi.openweather.model.currentweather;

import com.google.gson.annotations.SerializedName;

public record Wind(Double speed, @SerializedName("deg") Double degrees, Double gust) {}
