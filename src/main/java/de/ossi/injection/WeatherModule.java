package de.ossi.injection;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import de.ossi.model.CurrentWeatherConverter;
import de.ossi.model.ForecastConverter;
import de.ossi.model.WeatherConverter;
import de.ossi.model.currentweather.CurrentWeather;
import de.ossi.model.forecast.Forecast;
import de.ossi.service.CurrentWeatherService;
import de.ossi.service.ForecastService;
import de.ossi.service.WeatherService;

public class WeatherModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(new TypeLiteral<WeatherService<Forecast>>() {}).to(new TypeLiteral<ForecastService>() {});
        bind(new TypeLiteral<WeatherConverter<Forecast>>() {}).to(new TypeLiteral<ForecastConverter>() {});

        bind(new TypeLiteral<WeatherService<CurrentWeather>>() {}).to(new TypeLiteral<CurrentWeatherService>() {});
        bind(new TypeLiteral<WeatherConverter<CurrentWeather>>() {}).to(new TypeLiteral<CurrentWeatherConverter>() {});
    }
}
