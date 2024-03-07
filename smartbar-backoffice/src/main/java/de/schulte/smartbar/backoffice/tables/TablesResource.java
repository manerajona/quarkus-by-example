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

    private final TablesRepository tablesRepository;

    @Inject
    public TablesResource(TableMapper mapper, TablesRepository tablesRepository) {
        this.mapper = mapper;
        this.tablesRepository = tablesRepository;
    }

    @Override
    public Response tablesGet() {
        final List<Table> tables = tablesRepository.listAll();
        return Response.ok(tables.stream().map(mapper::mapToApiTable).toList())
                       .build();
    }

    @Override
    public Response tablesPost(ApiTable apiTable) {
        final Table table = new Table();
        mapper.mapToTable(apiTable, table);
        tablesRepository.persist(table);
        return Response.created(URI.create("/tables/" + table.getId())).build();
    }

    @Override
    public Response tablesTableIdDelete(Long tableId) {
        final Optional<Table> table = tablesRepository.findByIdOptional(tableId);
        if (table.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        tablesRepository.delete(table.get());
        return Response.ok().build();
    }

    @Override
    public Response tablesTableIdGet(Long tableId) {
        final Optional<Table> table = tablesRepository.findByIdOptional(tableId);
        if (table.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(mapper.mapToApiTable(table.get())).build();
    }

    @Override
    public Response tablesTableIdPut(Long tableId, ApiTable apiTable) {
        final Optional<Table> existingTable = tablesRepository.findByIdOptional(tableId);
        if (existingTable.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        final Table table = existingTable.get();
        mapper.mapToTable(apiTable, table);
        return Response.ok().build();
    }
}
