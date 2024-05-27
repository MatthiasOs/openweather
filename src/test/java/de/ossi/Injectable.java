package de.ossi;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.ossi.injection.HttpClientModule;

public interface Injectable {
    Injector injector = Guice.createInjector(new HttpClientModule());
}
