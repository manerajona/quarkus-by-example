package de.schulte.smartbar.backoffice.articles;

import de.schulte.smartbar.backoffice.categories.Category;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

public interface ArticlesResource extends PanacheEntityResource<Article, Long> {

    @GET
    @Path("/category/{categoryId}")
    @Produces({"application/json"})
    default Response listByCategory(@PathParam("categoryId") Long categoryId) {
        final Optional<Category> category = Category.findByIdOptional(categoryId);
        if (category.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        final List<Article> articles = Article.listByCategory(category.get());
        return Response.ok(articles).build();
    }

}
