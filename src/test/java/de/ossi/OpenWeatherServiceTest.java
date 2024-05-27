package de.ossi;

import de.ossi.model.Coord;
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

@SuppressWarnings({"unchecked", "rawtypes"})
@ExtendWith({MockitoExtension.class})
class OpenWeatherServiceTest {

    @Mock
    CurrentWeatherConverter converter;
    @Mock
    HttpClient mockClient;
    @InjectMocks
    OpenWeatherService service;


    @ParameterizedTest
    @ValueSource(ints = {100, 101, 401, 404})
    void whenHttpStatusNotSuccessShouldThrowException(Integer statusCode) throws Exception {
        HttpResponse errorResponse = createResponse(statusCode);
        when(mockClient.send(any(), any())).thenReturn(errorResponse);
        Assertions.assertThatIllegalStateException()
                  .isThrownBy(() -> service.readWeather(Coord.NUERNBERG))
                  .withMessageContaining("Status: " + statusCode);
    }

    @Test
    void whenHttpStatusSuccessShouldNotThrowException() throws Exception {
        int statusCodeSuccess = 200;
        HttpResponse errorResponse = createResponse(statusCodeSuccess);
        when(mockClient.send(any(), any())).thenReturn(errorResponse);
        Assertions.assertThatNoException().isThrownBy(() ->
                service.readWeather(Coord.NUERNBERG));
    }

    HttpResponse createResponse(int statusCode) {
        HttpResponse<String> errorResponse = (HttpResponse<String>) mock(HttpResponse.class);
        when(errorResponse.statusCode()).thenReturn(statusCode);
        return errorResponse;
    }

}