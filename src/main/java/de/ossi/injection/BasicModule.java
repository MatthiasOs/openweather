package de.ossi.injection;

import com.google.inject.AbstractModule;

import java.net.http.HttpClient;

public class BasicModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(HttpClient.class).toProvider(HttpClient::newHttpClient);
    }
}
