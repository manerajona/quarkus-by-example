package de.schulte.smartbar.backoffice.categories;

import de.schulte.smartbar.backoffice.api.model.Category;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoriesService {

    public Category get() {
        return new Category().name("very fancy drink");
    }

}
