# openweather

HttpService to Query https://openweathermap.org/current for Weather Data.

## Uses

- Javas HttpClient for the Request
- Google Gson for JSON Convertion
- Google Guise for Dependency Injection

## OpenWeather API Key

A personal key is needed to query the OpenWeather API, you can find the key in your OpenWeather account.

This Key as to be in an environment variable named "OPENWEATHER_API_KEY".

- Windows: `setx OPENWEATHER_API_KEY your_api_key`
- Unix/Linux/MacOS: `export API_KEY="your_api_key"`