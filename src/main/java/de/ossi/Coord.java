package de.ossi;

import com.google.gson.annotations.SerializedName;

public record Coord(@SerializedName("lat") Double latitude, @SerializedName("lon") Double longitude) {
    public static final Coord NUERNBERG = new Coord(49.4541, 11.0768);
}
