package de.ossi;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.ossi.injection.HttpClientModule;
import de.ossi.injection.WeatherModule;

public interface Injectable {
    Injector injector = Guice.createInjector(new HttpClientModule(), new WeatherModule());
}
