package de.schulte.smartbar.backoffice.articles;

import de.schulte.smartbar.backoffice.api.ArticlesApi;
import de.schulte.smartbar.backoffice.api.model.ApiArticle;
import de.schulte.smartbar.backoffice.categories.CategoriesService;
import de.schulte.smartbar.backoffice.categories.Category;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.Optional;

public class ArticlesResource implements ArticlesApi {

    private final ArticlesService articlesService;

    private final CategoriesService categoriesService;

    private final ArticleMapper mapper;

    @Inject
    public ArticlesResource(ArticlesService articlesService, CategoriesService categoriesService, ArticleMapper mapper) {
        this.articlesService = articlesService;
        this.categoriesService = categoriesService;
        this.mapper = mapper;
    }

    @Override
    public Response articlesArticleIdDelete(Long articleId) {
        final Optional<Article> article = articlesService.deleteById(articleId);
        if (article.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().build();
    }

    @Override
    public Response articlesArticleIdGet(Long articleId) {
        final Optional<Article> article = articlesService.getById(articleId);
        if (article.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(mapper.mapToApiArticle(article.get())).build();
    }

    @Override
    public Response articlesArticleIdPut(Long articleId, ApiArticle apiArticle) {
        final Optional<Article> existingArticle = articlesService.getById(articleId);
        if (existingArticle.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        final Article article = existingArticle.get();
        mapper.mapToArticle(apiArticle, article);
        articlesService.update(article);
        return Response.ok().build();
    }

    @Override
    public Response articlesGet() {
        final List<Article> articles = articlesService.listAll();
        return Response.ok(articles.stream().map(mapper::mapToApiArticle).toList())
                       .build();
    }

    @Override
    public Response articlesPost(Long xCategoryId, ApiArticle apiArticle) {
        final Optional<Category> category = categoriesService.getById(xCategoryId);
        if(category.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        final Article article = new Article();
        mapper.mapToArticle(apiArticle, article);
        article.setCategory(category.get());
        final Article persitedArticle = articlesService.persit(article);
        return Response.created(URI.create("/articles/" + persitedArticle.getId())).build();
    }

}
