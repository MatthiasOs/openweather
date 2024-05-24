package de.ossi.model;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import de.ossi.json.KelvinToCelsiusConverter;

public record Main(@JsonAdapter(KelvinToCelsiusConverter.class) Double temp,
                   @JsonAdapter(KelvinToCelsiusConverter.class) @SerializedName("feels_like") Double feelsLike) {}
