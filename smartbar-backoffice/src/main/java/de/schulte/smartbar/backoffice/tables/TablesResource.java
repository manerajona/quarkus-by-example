package de.schulte.smartbar.backoffice.tables;

import de.schulte.smartbar.backoffice.api.TablesApi;
import de.schulte.smartbar.backoffice.api.model.ApiTable;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Transactional
public class TablesResource implements TablesApi {

    private final TableMapper mapper;

    @Inject
    public TablesResource(TableMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Response tablesGet() {
        final List<Table> tables = Table.listAll();
        return Response.ok(tables.stream().map(mapper::mapToApiTable).toList())
                       .build();
    }

    @Override
    public Response tablesPost(ApiTable apiTable) {
        final Table table = new Table();
        mapper.mapToTable(apiTable, table);
        table.persist();
        return Response.created(URI.create("/tables/" + table.id)).build();
    }

    @Override
    public Response tablesTableIdDelete(Long tableId) {
        final Optional<Table> table = Table.findByIdOptional(tableId);
        if (table.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        table.get().delete();
        return Response.ok().build();
    }

    @Override
    public Response tablesTableIdGet(Long tableId) {
        final Optional<Table> table = Table.findByIdOptional(tableId);
        if (table.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(mapper.mapToApiTable(table.get())).build();
    }

    @Override
    public Response tablesTableIdPut(Long tableId, ApiTable apiTable) {
        final Optional<Table> existingTable = Table.findByIdOptional(tableId);
        if (existingTable.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        final Table table = existingTable.get();
        mapper.mapToTable(apiTable, table);
        return Response.ok().build();
    }
}
