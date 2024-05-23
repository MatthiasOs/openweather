package model;

import com.google.gson.annotations.SerializedName;

public class Coord {
    public static final Coord NUERNBERG = new Coord(49.45421000, 11.07752000);

    @SerializedName("lon")
    Double longitude;
    @SerializedName("lat")
    Double latitude;

    Coord(Double longitude, Double latitude) {
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
