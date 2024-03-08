package de.schulte.smartbar.backoffice.articles;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;

public interface ArticlesResource extends PanacheEntityResource<Article, Long> {

}
