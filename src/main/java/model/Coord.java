package model;

import com.google.gson.annotations.SerializedName;

public class Coord {
    public static final Coord NUERNBERG = new Coord(49.4541, 11.0768);

    @SerializedName("lat")
    Double latitude;
    @SerializedName("lon")
    Double longitude;

    Coord(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }


}
