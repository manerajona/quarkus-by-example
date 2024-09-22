package de.schulte.smartbar.orderclient.login;

import de.schulte.smartbar.orderclient.api.LoginsApi;
import de.schulte.smartbar.orderclient.api.model.LoginResponseBody;
import io.quarkus.cache.Cache;
import io.quarkus.cache.CacheName;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.concurrent.CompletionStage;

public class LoginsResource implements LoginsApi {

    private final MenuApiClient menuApiClient;

    private final MenuMapper menuMapper;

    private final Cache menuCache;

    @Inject
    public LoginsResource(@RestClient MenuApiClient menuApiClient, MenuMapper menuMapper, @CacheName("menu-cache") Cache menuCache) {
        this.menuApiClient = menuApiClient;
        this.menuMapper = menuMapper;
        this.menuCache = menuCache;
    }

    @Override
    public CompletionStage<LoginResponseBody> login(String tableId) {
        return menuCache.getAsync(tableId, id -> {
            return Uni.createFrom().completionStage(menuApiClient.getMenu().thenApply(menuMapper::mapToLoginResonse));
        }).subscribeAsCompletionStage();
    }

}
