package de.schulte.smartbar.backoffice.articles;

import de.schulte.smartbar.backoffice.api.model.Article;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArticlesService {

    public Article get() {
        return new Article().name("cola");
    }

}
