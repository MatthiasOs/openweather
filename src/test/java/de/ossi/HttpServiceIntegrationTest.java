package de.ossi;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.ossi.injection.BasicModule;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.DOUBLE;

class HttpServiceIntegrationTest {
    Injector injector = Guice.createInjector(new BasicModule());
    WeatherConverter converter = injector.getInstance(WeatherConverter.class);
    HttpService service = injector.getInstance(HttpService.class);

    @Test
    void shouldConvertHttpResponseToJson() throws Exception {
        String httpResponse = service.readCurrentWeather(Coord.NUERNBERG);
        assertThat(httpResponse).isNotEmpty();
        CurrentWeather currentWeather = converter.convert(httpResponse);

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