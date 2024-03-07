package de.schulte.smartbar.backoffice.articles;

import de.schulte.smartbar.backoffice.api.ArticlesApi;
import de.schulte.smartbar.backoffice.api.model.ApiArticle;
import de.schulte.smartbar.backoffice.categories.CategoriesRepository;
import de.schulte.smartbar.backoffice.categories.Category;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Transactional
public class ArticlesResource implements ArticlesApi {

    private final ArticleMapper mapper;

    private final ArticleRepository articleRepository;

    private final CategoriesRepository categoriesRepository;

    @Inject
    public ArticlesResource(ArticleMapper mapper, ArticleRepository articleRepository, CategoriesRepository categoriesRepository) {
        this.mapper = mapper;
        this.articleRepository = articleRepository;
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public Response articlesArticleIdDelete(Long articleId) {
        final Optional<Article> article = articleRepository.findByIdOptional(articleId);
        if (article.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        articleRepository.delete(article.get());
        return Response.ok().build();
    }

    @Override
    public Response articlesArticleIdGet(Long articleId) {
        final Optional<Article> article = articleRepository.findByIdOptional(articleId);
        if (article.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(mapper.mapToApiArticle(article.get())).build();
    }

    @Override
    public Response articlesArticleIdPut(Long articleId, ApiArticle apiArticle) {
        final Optional<Article> existingArticle = articleRepository.findByIdOptional(articleId);
        if (existingArticle.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        final Article article = existingArticle.get();
        mapper.mapToArticle(apiArticle, article);
        return Response.ok().build();
    }

    @Override
    public Response articlesGet() {
        final List<Article> articles = articleRepository.listAll();
        return Response.ok(articles.stream().map(mapper::mapToApiArticle).toList())
                       .build();
    }

    @Override
    public Response articlesPost(Long xCategoryId, ApiArticle apiArticle) {
        final Optional<Category> category = categoriesRepository.findByIdOptional(xCategoryId);
        if(category.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        final Article article = new Article();
        mapper.mapToArticle(apiArticle, article);
        article.setCategory(category.get());
        articleRepository.persist(article);
        return Response.created(URI.create("/articles/" + article.getId())).build();
    }

}
