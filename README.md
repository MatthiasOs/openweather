# 🌦️ OpenWeather Client

A lightweight Spring Boot application that queries the [OpenWeather API](https://openweathermap.org/) for current and
forecast weather data, maps the response to an internal data model, and exposes it via REST endpoints.

---

## 🧰 Technologies

- **Java 25**
- **Spring Boot 4.0.3**
- **Java HttpClient** (for HTTP requests)
- **Google Gson** (for JSON serialization/deserialization)
- **JUnit 5**, **AssertJ**, **Mockito** (for testing)

---

## 🔑 OpenWeather API Key

You need a personal API key from your [OpenWeather account](https://home.openweathermap.org/api_keys) to use the
service.

This key must be provided as an **environment variable** named `OPENWEATHER_API_KEY`.

### Windows (PowerShell)

```powershell
setx OPENWEATHER_API_KEY "your_api_key"
```

### macOS / Linux

```bash
export OPENWEATHER_API_KEY="your_api_key"
```

---

## 🚀 Run the Application

### Start via Maven

```bash
./mvnw spring-boot:run
```

### Or build and run the JAR

```bash
./mvnw clean package
java -jar target/openweather-0.0.1-SNAPSHOT.jar
```

The application will start on  
👉 **http://localhost:8081**

---

## 🌤️ Example Requests

### Get current weather

```
GET http://localhost:8081/api/v1/currentweather?latitude=49.4541&longitude=11.0768
```

### Get 5-day forecast

```
GET http://localhost:8081/api/v1/forecast?latitude=52.5200&longitude=13.4050
```

Both endpoints return JSON-mapped weather data based on the internal domain model.

---

## 🧪 Running Tests

Unit tests can be executed via:

```bash
./mvnw test
```

Integration tests are excluded from the default Maven lifecycle (see `maven-surefire-plugin` configuration) but can be
run manually in IntelliJ or with:

```bash
./mvnw -Dtest=*IntegrationTest test
```

---

## 🗂️ Project Structure

```
src
├── main
│   ├── java/de/ossi/openweather
│   │   ├── controller/      → REST controllers
│   │   ├── json/            → JSON De-/Serializer
│   │   ├── service/         → Business logic, HTTP requests
│   │   ├── model/           → Internal data model (mapped from OpenWeather JSON)
│   │   └── OpenweatherApplication.java → Main entry point
│   └── resources/
│       └── application.properties
└── test
    └── java/de/ossi/openweather
        ├── model/
        ├── WeatherServiceIntegrationTest.java
        └── WeatherServiceTest.java
```

---

## 🧩 Future Improvements / TODOs

- [ ] Add support for additional OpenWeather endpoints (e.g. air pollution, UV index)
- [ ] Implement caching for repeated queries

---

## 📄 License

This project is distributed under the MIT License.  
See [LICENSE](LICENSE) for more details.
