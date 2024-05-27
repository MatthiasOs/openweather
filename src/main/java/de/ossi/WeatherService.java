package de.ossi;

import de.ossi.model.Coord;
import de.ossi.model.Root;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

public interface WeatherService<T extends Root> {
    String OPENWEATHER_ENV = "OPENWEATHER_API_KEY";

    T readWeather(OpenWeatherEndpoint endpoint, Coord location) throws IOException, InterruptedException;

    /**
     * api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
     *
     * @see <a href="https://openweathermap.org/current">OpenWeather</a>
     */
    default URI createUri(OpenWeatherEndpoint openWeatherEndpoint, Coord location) {
        String openweatherApiKey = Optional.ofNullable(System.getenv(OPENWEATHER_ENV))
                                           .orElseThrow(ApiKeyNotFoundException::new);
        String url = "http://api.openweathermap.org/data/2.5/" + openWeatherEndpoint + "?lat=" + location.latitude() + "&lon=" + location.longitude() + "&appid=" + openweatherApiKey;
        return URI.create(url);
    }

    class ApiKeyNotFoundException extends RuntimeException {
        ApiKeyNotFoundException() {
            super("API Key not found in the System Environment Variable OPENWEATHER_API_KEY");
        }
    }

    enum OpenWeatherEndpoint {
        WEATHER, FORECAST;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}
