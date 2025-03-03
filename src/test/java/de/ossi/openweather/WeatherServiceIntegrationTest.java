package de.ossi.openweather;

import de.ossi.openweather.model.currentweather.Coord;
import de.ossi.openweather.model.currentweather.CurrentWeather;
import de.ossi.openweather.model.forecast.City;
import de.ossi.openweather.model.forecast.Forecast;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.DOUBLE;

/**
 * This test queries the REAL OpenWeather API.
 * Therefor an API Key is required, see the ReadMe for more infos.
 */
class WeatherServiceIntegrationTest implements Injectable {
    WeatherService weatherService = injector.getInstance(WeatherService.class);

    @Test
    void shouldConvertCurrentWeatherHttpResponseToJson() throws Exception {
        CurrentWeather currentWeather = weatherService.readCurrentWeather(Coord.NUERNBERG);

        assertThat(currentWeather)
                .hasNoNullFieldsOrProperties()
                .extracting(CurrentWeather::cityName)
                .isEqualTo("Nuremberg");
        SoftAssertions.assertSoftly(softly -> {
                    softly.assertThat(currentWeather.coord())
                          .extracting(Coord::latitude, DOUBLE)
                          .isCloseTo(Coord.NUERNBERG.latitude(), Offset.offset(0.5));
                    softly.assertThat(currentWeather.coord())
                          .extracting(Coord::longitude, DOUBLE)
                          .isCloseTo(Coord.NUERNBERG.longitude(), Offset.offset(0.5));
                }
        );
    }

    @Test
    void shouldConvertForecastHttpResponseToJson() throws Exception {
        Forecast forecast = weatherService.readForecast(Coord.NUERNBERG);
        assertThat(forecast)
                .hasNoNullFieldsOrProperties()
                .extracting(Forecast::city)
                .extracting(City::country, City::name)
                .containsExactly("DE", "Nuremberg");
    }
}