package de.schulte.smartbar.backoffice.tables;

import de.schulte.smartbar.backoffice.CrudService;
import de.schulte.smartbar.backoffice.api.model.ApiTable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class TablesService extends CrudService<Table> {

    public TablesService() {
        // Just for CDI requirements
        super(null);
    }

    @Inject
    public TablesService(EntityManager entityManager) {
        super(entityManager);
    }

    public ApiTable get() {
        return new ApiTable().name("Berlin");
    }

}
