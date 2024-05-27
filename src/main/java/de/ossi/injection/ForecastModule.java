package de.ossi.injection;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import de.ossi.ForecastConverter;
import de.ossi.OpenWeatherService;
import de.ossi.WeatherConverter;
import de.ossi.WeatherService;
import de.ossi.model.forecast.Forecast;

public class ForecastModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<WeatherService<Forecast>>() {}).to(new TypeLiteral<OpenWeatherService<Forecast>>() {});
        bind(new TypeLiteral<WeatherConverter<Forecast>>() {}).to(new TypeLiteral<ForecastConverter>() {});
    }
}
