package de.schulte.smartbar.backoffice;

import de.schulte.smartbar.backoffice.api.model.Table;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TablesService {

    public Table get() {
        return new Table().name("Berlin");
    }

}
