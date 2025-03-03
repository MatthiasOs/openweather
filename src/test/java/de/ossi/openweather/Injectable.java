package de.ossi.openweather;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.ossi.openweather.injection.HttpClientModule;

public interface Injectable {
    Injector injector = Guice.createInjector(new HttpClientModule());
}
