package model;

import com.google.gson.annotations.SerializedName;

public class Coord {
    @SerializedName("lon")
    Double longitude;
    @SerializedName("lat")
    Double latitude;
}
