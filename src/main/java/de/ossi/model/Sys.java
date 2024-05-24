package de.ossi.model;

import java.time.LocalDateTime;

public record Sys(String country, LocalDateTime sunrise, LocalDateTime sunset) {}

