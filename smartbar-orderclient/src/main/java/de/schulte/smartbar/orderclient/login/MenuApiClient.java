package de.schulte.smartbar.orderclient.login;

import de.schulte.smartbar.backoffice.api.model.ApiMenu;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.concurrent.CompletionStage;

@Path("/menu")
@RegisterRestClient
public interface MenuApiClient {

    @GET
    @Retry(delay = 6000)
    CompletionStage<ApiMenu> getMenu();

}
