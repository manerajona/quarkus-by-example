package de.schulte.smartbar.orderclient.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.schulte.smartbar.backoffice.api.model.ApiMenu;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;

@ApplicationScoped
public class MenuApiClientImpl implements MenuApiClient {

    private final String apiUrl;

    public MenuApiClientImpl(@ConfigProperty(name = "backoffice.menuapi.url") String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public ApiMenu getMenu() {
        final var request = new HttpGet(apiUrl);
        try(CloseableHttpClient client = HttpClients.createDefault(); CloseableHttpResponse response = client.execute(request);) {
            return new ObjectMapper().readValue(response.getEntity().getContent(), ApiMenu.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
