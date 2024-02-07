package de.schulte.smartbar.backoffice.api;

import java.net.URI;
import java.util.List;

import de.schulte.smartbar.backoffice.TablesService;
import de.schulte.smartbar.backoffice.api.model.Table;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

public class TablesResource implements TablesApi {

    private final TablesService tablesService;

    @Inject
    public TablesResource(TablesService tablesService) {
        this.tablesService = tablesService;
    }

    @Override
    public Response tablesGet() {
        return Response.ok(List.of(tablesService.get())).build();
    }

    @Override
    public Response tablesPost(Table table) {
        return Response.created(URI.create("todo")).build();
    }

    @Override
    public Response tablesTableIdDelete(String tableId) {
        return Response.ok().build();
    }

    @Override
    public Response tablesTableIdGet(String tableId) {
        return Response.ok(tablesService.get()).build();
    }

    @Override
    public Response tablesTableIdPut(String tableId, Table table) {
        return Response.ok().build();
    }
}
