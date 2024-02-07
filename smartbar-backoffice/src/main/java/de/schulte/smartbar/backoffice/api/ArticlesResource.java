package de.schulte.smartbar.backoffice.api;

import java.net.URI;
import java.util.List;

import de.schulte.smartbar.backoffice.ArticlesService;
import de.schulte.smartbar.backoffice.api.model.Article;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

public class ArticlesResource implements ArticlesApi {

    private final ArticlesService articlesService;

    @Inject
    public ArticlesResource(ArticlesService articlesService) {
        this.articlesService = articlesService;
    }

    @Override
    public Response articlesArticleIdDelete(String articleId) {
        return Response.ok().build();
    }

    @Override
    public Response articlesArticleIdGet(String articleId) {
        return Response.ok(articlesService.get()).build();
    }

    @Override
    public Response articlesArticleIdPut(String articleId, Article article) {
        return Response.ok().build();
    }

    @Override
    public Response articlesGet() {
        return Response.ok(List.of(articlesService.get())).build();
    }

    @Override
    public Response articlesPost(Article article) {
        return Response.created(URI.create("todo")).build();
    }
}
