package de.ossi;

import de.ossi.WeatherService.OpenWeatherEndpoint;
import de.ossi.model.Coord;
import de.ossi.model.CurrentWeather;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings({"unchecked", "rawtypes"})
@ExtendWith({MockitoExtension.class})
class OpenWeatherServiceTest {

    @Mock
    CurrentWeatherConverter converter;
    @Mock
    HttpClient mockClient;
    @InjectMocks
    OpenWeatherService<CurrentWeather> service;

    @Nested
    class Weather {

    }


    @ParameterizedTest
    @CsvSource({
            "100, WEATHER",
            "401, WEATHER",
            "100, FORECAST",
            "401, FORECAST",
    })
    void whenHttpStatusNotSuccessShouldThrowException(Integer statusCode, OpenWeatherEndpoint endpoint) throws Exception {
        HttpResponse errorResponse = createResponse(statusCode);
        when(mockClient.send(any(), any())).thenReturn(errorResponse);
        Assertions.assertThatIllegalStateException()
                  .isThrownBy(() -> service.readWeather(endpoint, Coord.NUERNBERG))
                  .withMessageContaining("Status: " + statusCode);
    }

    @EnumSource
    @ParameterizedTest
    void whenHttpStatusSuccessShouldNotThrowException(OpenWeatherEndpoint endpoint) throws Exception {
        int statusCodeSuccess = 200;
        HttpResponse errorResponse = createResponse(statusCodeSuccess);
        when(mockClient.send(any(), any())).thenReturn(errorResponse);
        Assertions.assertThatNoException().isThrownBy(() ->
                service.readWeather(endpoint, Coord.NUERNBERG));
    }

    HttpResponse createResponse(int statusCode) {
        HttpResponse<String> errorResponse = (HttpResponse<String>) mock(HttpResponse.class);
        when(errorResponse.statusCode()).thenReturn(statusCode);
        return errorResponse;
    }

}