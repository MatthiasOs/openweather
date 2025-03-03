package de.ossi.openweather;

import de.ossi.openweather.model.WeatherConverter;
import de.ossi.openweather.model.currentweather.Coord;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
@ExtendWith({MockitoExtension.class})
class WeatherServiceTest {

    @Mock
    WeatherConverter converter;
    @Mock
    HttpClient mockClient;
    @InjectMocks
    WeatherService service;

    @ParameterizedTest
    @ValueSource(ints = {100, 401, 404})
    void whenHttpStatusNotSuccessShouldThrowException(Integer statusCode) throws Exception {
        givenResponse(statusCode);
        Assertions.assertThatIllegalStateException()
                  .isThrownBy(() -> service.readCurrentWeather(Coord.NUERNBERG))
                  .withMessageContaining("Status: " + statusCode);
    }

    @Test
    void whenHttpStatusSuccessShouldNotThrowException() throws Exception {
        int statusCodeSuccess = 200;
        givenResponse(statusCodeSuccess);
        Assertions.assertThatNoException().isThrownBy(() ->
                service.readCurrentWeather(Coord.NUERNBERG));
    }

    void givenResponse(int statusCode) throws Exception {
        HttpResponse<Object> errorResponse = mock(HttpResponse.class);
        when(errorResponse.statusCode()).thenReturn(statusCode);
        when(mockClient.send(any(), any())).thenReturn(errorResponse);
    }

}