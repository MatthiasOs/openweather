package de.ossi.openweather.model.currentweather;

import java.time.LocalDateTime;

public record Sys(String country, LocalDateTime sunrise, LocalDateTime sunset) {}

