package de.ossi.openweather;

import de.ossi.openweather.model.currentweather.Coord;
import de.ossi.openweather.model.currentweather.CurrentWeather;
import de.ossi.openweather.model.forecast.City;
import de.ossi.openweather.model.forecast.Forecast;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.DOUBLE;

/**
 * This test queries the REAL OpenWeather API.
 * Therefor an API Key is required, see the ReadMe for more infos.
 */
@SpringBootTest
class WeatherServiceIntegrationTest {
    @Autowired
    private WeatherService weatherService;

    @Test
    void shouldConvertCurrentWeatherHttpResponseToJson() throws Exception {
        var currentWeather = weatherService.readCurrentWeather(Coord.NUERNBERG);
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
        });
    }

    @Test
    void shouldConvertForecastHttpResponseToJson() throws Exception {
        var forecast = weatherService.readForecast(Coord.NUERNBERG);
        assertThat(forecast)
                .hasNoNullFieldsOrProperties()
                .extracting(Forecast::city)
                .extracting(City::country, City::name)
                .containsExactly("DE", "Nuremberg");
        assertThat(forecast)
                .extracting(Forecast::weathers, InstanceOfAssertFactories.list(CurrentWeather.class))
                .isNotEmpty()
                .allSatisfy(weather -> {
                    assertThat(weather)
                            .extracting(
                                    w -> w.main().temp(),
                                    w -> w.main().feelsLike(),
                                    w -> w.wind().speed(),
                                    w -> w.wind().degrees(),
                                    w -> w.wind().gust(),
                                    w -> w.weathers().getFirst().main(),
                                    w -> w.weathers().getFirst().description()
                            )
                            .doesNotContainNull();
                });
    }
}