package de.ossi;

import com.google.inject.Guice;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import de.ossi.injection.CurrentWeatherModule;
import de.ossi.model.Coord;
import de.ossi.model.CurrentWeather;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.DOUBLE;

/**
 * This test queries the REAL OpenWeather API.
 * Therefor an API Key is required, see the ReadMe for more infos.
 */
class OpenWeatherServiceIntegrationTest {
    WeatherService<CurrentWeather> service = Guice.createInjector(new CurrentWeatherModule())
                                                  .getInstance(Key.get(new TypeLiteral<>() {}));

    @Test
    void shouldConvertHttpResponseToJson() throws Exception {
        CurrentWeather currentWeather = service.readWeather(OpenWeatherEndpoint.WEATHER, Coord.NUERNBERG);

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
}