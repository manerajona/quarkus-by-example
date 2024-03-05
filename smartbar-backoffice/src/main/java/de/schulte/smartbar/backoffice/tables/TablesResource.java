package de.schulte.smartbar.backoffice.tables;

import de.schulte.smartbar.backoffice.api.TablesApi;
import de.schulte.smartbar.backoffice.api.model.ApiTable;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.Optional;

public class TablesResource implements TablesApi {

    private final TablesService tablesService;

    private final TableMapper mapper;

    @Inject
    public TablesResource(TablesService tablesService, TableMapper mapper) {
        this.tablesService = tablesService;
        this.mapper = mapper;
    }

    @Override
    public Response tablesGet() {
        final List<Table> tables = tablesService.listAll();
        return Response.ok(tables.stream().map(mapper::mapToApiTable).toList())
                       .build();
    }

    @Override
    public Response tablesPost(ApiTable apiTable) {
        final Table table = new Table();
        mapper.mapToTable(apiTable, table);
        final Table persitedTable = tablesService.persit(table);
        return Response.created(URI.create("/tables/" + persitedTable.getId())).build();
    }

    @Override
    public Response tablesTableIdDelete(Long tableId) {
        final Optional<Table> table = tablesService.deleteById(tableId);
        if (table.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().build();
    }

    @Override
    public Response tablesTableIdGet(Long tableId) {
        final Optional<Table> table = tablesService.getById(tableId);
        if (table.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(mapper.mapToApiTable(table.get())).build();
    }

    @Override
    public Response tablesTableIdPut(Long tableId, ApiTable apiTable) {
        final Optional<Table> existingTable = tablesService.getById(tableId);
        if (existingTable.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        final Table table = existingTable.get();
        mapper.mapToTable(apiTable, table);
        tablesService.update(table);
        return Response.ok().build();
    }
}
