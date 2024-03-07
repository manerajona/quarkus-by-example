package de.schulte.smartbar.backoffice.tables;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TablesRepository implements PanacheRepository<Table> {
}
