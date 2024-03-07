package de.schulte.smartbar.backoffice.categories;

import de.schulte.smartbar.backoffice.api.CategoriesApi;
import de.schulte.smartbar.backoffice.api.model.ApiCategory;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Transactional
public class CategoriesResource implements CategoriesApi {

    private final CategoryMapper mapper;

    private final CategoriesRepository categoriesRepository;

    @Inject
    public CategoriesResource(CategoryMapper mapper, CategoriesRepository categoriesRepository) {
        this.mapper = mapper;
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public Response categoriesCategoryIdDelete(Long categoryId) {
        final Optional<Category> category = categoriesRepository.findByIdOptional(categoryId);
        if (category.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        categoriesRepository.delete(category.get());
        return Response.ok().build();
    }

    @Override
    public Response categoriesCategoryIdGet(Long categoryId) {
        final Optional<Category> category = categoriesRepository.findByIdOptional(categoryId);
        if (category.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(mapper.mapToApiCategory(category.get())).build();
    }

    @Override
    public Response categoriesCategoryIdPut(Long categoryId, ApiCategory apiCategory) {
        final Optional<Category> existingCategory = categoriesRepository.findByIdOptional(categoryId);
        if (existingCategory.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        final Category category = existingCategory.get();
        mapper.mapToCategory(apiCategory, category);
        return Response.ok().build();
    }

    @Override
    public Response categoriesGet() {
        final List<Category> categories = categoriesRepository.listAll();
        return Response.ok(categories.stream().map(mapper::mapToApiCategory).toList())
                       .build();
    }

    @Override
    public Response categoriesPost(ApiCategory apiCategory) {
        final Category category = new Category();
        mapper.mapToCategory(apiCategory, category);
        categoriesRepository.persist(category);
        return Response.created(URI.create("/categories/" + category.getId())).build();
    }

}
