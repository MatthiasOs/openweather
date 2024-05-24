package de.ossi;

import com.google.inject.Guice;
import de.ossi.injection.BasicModule;
import de.ossi.model.Coord;
import de.ossi.model.CurrentWeather;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.DOUBLE;

class HttpServiceIntegrationTest {
    HttpService service = Guice.createInjector(new BasicModule()).getInstance(HttpService.class);

    @Test
    void shouldConvertHttpResponseToJson() throws Exception {
        CurrentWeather currentWeather = service.readCurrentWeather(Coord.NUERNBERG);

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