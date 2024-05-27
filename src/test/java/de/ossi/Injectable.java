package de.ossi;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.ossi.injection.CurrentWeatherModule;
import de.ossi.injection.HttpClientModule;

public class Injectable {
    protected Injector injector = Guice.createInjector(new HttpClientModule(), new CurrentWeatherModule());
}
