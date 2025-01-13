package de.schulte.smartbar.orderclient.login;

import io.smallrye.mutiny.Uni;

public interface LoginService {

    Uni<String> createNewLogin(final String tableId);

    Uni<Boolean> hasLogin(final String tableId);
}
