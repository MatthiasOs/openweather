package de.ossi.injection;

import com.google.inject.TypeLiteral;
import de.ossi.CurrentWeatherConverter;
import de.ossi.OpenWeatherService;
import de.ossi.WeatherConverter;
import de.ossi.WeatherService;
import de.ossi.model.CurrentWeather;

public class CurrentWeatherModule extends HttpClientModule {

    @Override
    protected void configure() {
        super.configure();
        bind(new TypeLiteral<WeatherService<CurrentWeather>>() {}).to(new TypeLiteral<OpenWeatherService<CurrentWeather>>() {});
        bind(new TypeLiteral<WeatherConverter<CurrentWeather>>() {}).to(new TypeLiteral<CurrentWeatherConverter>() {});
    }
}
