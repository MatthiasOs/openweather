# openweather

App to query https://openweathermap.org/ for Weather Data.

## Uses

- Java 23
- SpringBoot 3.4.3
- Java's HttpClient for the Request
- Google Gson for JSON conversion

## OpenWeather API Key

A personal key is needed to query the OpenWeather API, you can find the key in your OpenWeather account.

This key has to be in an environment variable named `OPENWEATHER_API_KEY`.

- Windows: `setx OPENWEATHER_API_KEY your_api_key`
- Unix/Linux/MacOS: `export API_KEY="your_api_key"`

## Usage

Start the Application:

```bash
./mvnw spring-boot:run
```

Example Request:

http://localhost:8081/api/v1/currentweather?latitude=49.4541&longitude=11.0768

http://localhost:8081/api/v1/forecast?latitude=52.5200&longitude=13.4050
