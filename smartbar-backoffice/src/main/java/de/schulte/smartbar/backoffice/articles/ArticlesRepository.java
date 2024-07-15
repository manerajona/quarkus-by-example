package de.schulte.smartbar.backoffice.articles;

import de.schulte.smartbar.backoffice.categories.Category;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ArticlesRepository implements PanacheRepositoryBase<Article, Long> {

    public List<Article> listAllInCategory(final Category category) {
        return list("category", category);
    }

}
