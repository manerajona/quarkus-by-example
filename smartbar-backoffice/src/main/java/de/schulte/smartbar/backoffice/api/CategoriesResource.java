package de.schulte.smartbar.backoffice.api;

import java.util.List;

import de.schulte.smartbar.backoffice.api.model.Category;

public class CategoriesResource implements CategoriesApi {

    private final Category category = new Category().name("drinks");

    @Override
    public void categoriesCategoryIdDelete(String categoryId) {

    }

    @Override
    public Category categoriesCategoryIdGet(String categoryId) {
        return category;
    }

    @Override
    public void categoriesCategoryIdPut(String categoryId, Category category) {

    }

    @Override
    public List<Category> categoriesGet() {
        return List.of(category);
    }

    @Override
    public void categoriesPost(Category category) {

    }
}
