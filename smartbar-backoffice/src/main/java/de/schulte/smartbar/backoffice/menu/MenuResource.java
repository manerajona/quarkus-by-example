package de.schulte.smartbar.backoffice.menu;

import de.schulte.smartbar.backoffice.api.MenuApi;
import de.schulte.smartbar.backoffice.api.model.ApiCategory;
import de.schulte.smartbar.backoffice.api.model.ApiMenu;
import de.schulte.smartbar.backoffice.articles.ArticlesMapper;
import de.schulte.smartbar.backoffice.articles.ArticlesRepository;
import de.schulte.smartbar.backoffice.categories.CategoriesRepository;
import de.schulte.smartbar.backoffice.categories.Category;
import jakarta.inject.Inject;

public class MenuResource implements MenuApi {

    private final CategoriesRepository categoriesRepository;

    private final ArticlesRepository articlesRepository;

    private final CategoriesMapper categoriesMapper;

    private final ArticlesMapper articlesMapper;

    @Inject
    public MenuResource(CategoriesRepository categoriesRepository, ArticlesRepository articlesRepository, CategoriesMapper categoriesMapper, ArticlesMapper articlesMapper) {
        this.categoriesRepository = categoriesRepository;
        this.articlesRepository = articlesRepository;
        this.categoriesMapper = categoriesMapper;
        this.articlesMapper = articlesMapper;
    }

    @Override
    public ApiMenu getMenu() {
        final var apiCategories = categoriesRepository.listAll().stream()
                                   .map(this::apiCategoriesFromCategories).toList();
        final var apiMenu = new ApiMenu();
        apiMenu.setCategories(apiCategories);
        return apiMenu;
    }

    private ApiCategory apiCategoriesFromCategories(final Category category) {
        final var apiCategory = categoriesMapper.mapToApiCategory(category);
        final var articles = articlesRepository.listAllInCategory(category);
        apiCategory.setArticles(articles.stream().map(articlesMapper::mapFrom).toList());
        return apiCategory;
    }

}
