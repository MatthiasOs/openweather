package model;

import com.google.gson.annotations.SerializedName;

public class Wind {
    Double speed;
    @SerializedName("deg")
    Double degrees;
    Double gust;
}
