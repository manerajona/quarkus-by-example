package de.schulte.smartbar.backoffice.articles;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArticleRepository implements PanacheRepository<Article> {
}
