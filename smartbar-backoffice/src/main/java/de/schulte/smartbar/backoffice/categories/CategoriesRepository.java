package de.schulte.smartbar.backoffice.categories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoriesRepository implements PanacheRepositoryBase<Category, Long> {
}
