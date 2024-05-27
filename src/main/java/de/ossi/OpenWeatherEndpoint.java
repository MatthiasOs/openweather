package de.ossi;

public enum OpenWeatherEndpoint {
    WEATHER, FORECAST;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
