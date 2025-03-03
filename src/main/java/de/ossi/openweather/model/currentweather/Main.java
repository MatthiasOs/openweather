package de.ossi.openweather.model.currentweather;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import de.ossi.openweather.json.KelvinToCelsiusConverter;

public record Main(@JsonAdapter(KelvinToCelsiusConverter.class) Double temp,
                   @JsonAdapter(KelvinToCelsiusConverter.class) @SerializedName("feels_like") Double feelsLike) {}
