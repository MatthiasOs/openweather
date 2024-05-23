import com.google.gson.Gson;
import model.City;

public class WeatherConverter {
    private final Gson gson = new Gson();

    public City convert(String json) {
        return gson.fromJson(json, City.class);
    }
}
