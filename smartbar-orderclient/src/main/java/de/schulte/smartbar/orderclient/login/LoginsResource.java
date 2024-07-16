package de.schulte.smartbar.orderclient.login;

import de.schulte.smartbar.orderclient.api.LoginsApi;
import de.schulte.smartbar.orderclient.api.model.LoginResponseBody;
import jakarta.inject.Inject;

public class LoginsResource implements LoginsApi {

    private final MenuApiClient menuApiClient;

    private final MenuMapper menuMapper;

    @Inject
    public LoginsResource(MenuApiClient menuApiClient, MenuMapper menuMapper) {
        this.menuApiClient = menuApiClient;
        this.menuMapper = menuMapper;
    }

    @Override
    public LoginResponseBody login(String tableId) {
        return menuMapper.mapToLoginResonse(menuApiClient.getMenu());
    }

}
