package de.schulte.smartbar.backoffice.articles;

import de.schulte.smartbar.backoffice.categories.Category;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ArticleRepository implements PanacheRepository<Article> {

    public List<Article> listByCategory(Category category) {
        return list("category", Sort.by("price", Sort.Direction.Descending), category);
    }

}
