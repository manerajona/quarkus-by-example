package de.schulte.smartbar.orderclient.login;

import io.quarkus.redis.client.RedisClientName;
import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.keys.ReactiveKeyCommands;
import io.quarkus.redis.datasource.value.ReactiveValueCommands;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class RedisLoginService implements LoginService {

    private final ReactiveValueCommands<String, String> tokens;

    private final ReactiveKeyCommands<String> keys;

    @Inject
    public RedisLoginService(@RedisClientName("logins") ReactiveRedisDataSource dataSource) {
        this.tokens = dataSource.value(String.class);
        this.keys = dataSource.key();
    }

    @Override
    public Uni<String> createNewLogin(final String tableId) {
        final var token = UUID.randomUUID().toString();
        return tokens.set(tableId, token).chain(v-> keys.expire(tableId, 20)).map(v -> token);
    }

    @Override
    public Uni<Boolean> hasLogin(final String tableId) {
        return keys.exists(tableId);
    }

}
