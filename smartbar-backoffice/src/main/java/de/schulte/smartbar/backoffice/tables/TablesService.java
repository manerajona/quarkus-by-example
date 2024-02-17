package de.schulte.smartbar.backoffice.tables;

import de.schulte.smartbar.backoffice.api.model.ApiTable;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TablesService {

    public ApiTable get() {
        return new ApiTable().name("Berlin");
    }

}
