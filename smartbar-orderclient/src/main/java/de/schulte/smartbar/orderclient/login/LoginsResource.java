package de.schulte.smartbar.orderclient.login;

import de.schulte.smartbar.orderclient.api.LoginsApi;
import de.schulte.smartbar.orderclient.api.model.LoginResponseBody;
import io.quarkus.cache.CacheResult;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.concurrent.CompletionStage;

public class LoginsResource implements LoginsApi {

    private final MenuApiClient menuApiClient;

    private final MenuMapper menuMapper;

    @Inject
    public LoginsResource(@RestClient MenuApiClient menuApiClient, MenuMapper menuMapper) {
        this.menuApiClient = menuApiClient;
        this.menuMapper = menuMapper;
    }

    @Override
    @CacheResult(cacheName = "menu-cache")
    public CompletionStage<LoginResponseBody> login(String tableId) {
        return menuApiClient.getMenu().thenApply(menuMapper::mapToLoginResonse);
    }

}
