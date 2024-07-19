package de.schulte.smartbar.orderclient.login;

import de.schulte.smartbar.backoffice.api.model.ApiMenu;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;
import java.util.concurrent.CompletionStage;

@Path("/menu")
@RegisterRestClient
public interface MenuApiClient {

    @GET
    @Timeout(15)
    @Retry(maxRetries = 2)
    @Fallback(fallbackMethod = "getFallbackMenu")
    CompletionStage<ApiMenu> getMenu();

    default CompletionStage<ApiMenu> getFallbackMenu() {
        final var fallback = new ApiMenu();
        fallback.setCategories(List.of());
        return Uni.createFrom().item(fallback).subscribeAsCompletionStage();
    }

}
