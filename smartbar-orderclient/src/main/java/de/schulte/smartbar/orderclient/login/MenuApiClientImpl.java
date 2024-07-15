package de.schulte.smartbar.orderclient.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.schulte.smartbar.backoffice.api.model.ApiMenu;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@ApplicationScoped
public class MenuApiClientImpl implements MenuApiClient {

    private final String apiUrl;

    public MenuApiClientImpl(@ConfigProperty(name = "backoffice.menuapi.url") String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public ApiMenu getMenu() {
        try {
            final var request = HttpRequest.newBuilder(new URI(apiUrl)).GET().build();
            final var httpClient = HttpClient.newBuilder().build();
            final var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return new ObjectMapper().readValue(response.body(), ApiMenu.class);
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
