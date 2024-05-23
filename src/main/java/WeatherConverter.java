import com.google.gson.Gson;
import model.WeatherForecast;

public class WeatherConverter {
    private final Gson gson = new Gson();

    public WeatherForecast convert(String json) {
        return gson.fromJson(json, WeatherForecast.class);
    }
}
