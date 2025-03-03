package de.ossi.openweather;


import de.ossi.openweather.model.currentweather.Coord;
import de.ossi.openweather.model.currentweather.CurrentWeather;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {this.weatherService = weatherService;}

    @GetMapping("/currentweather")
    public CurrentWeather getCurrentWeatherAt(
            @RequestParam double latitude,
            @RequestParam double longitude) throws IOException, InterruptedException {
        return weatherService.readCurrentWeather(new Coord(latitude, longitude));
    }
}
