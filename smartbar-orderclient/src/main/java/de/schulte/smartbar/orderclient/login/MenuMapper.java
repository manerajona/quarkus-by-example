package de.schulte.smartbar.orderclient.login;

import de.schulte.smartbar.backoffice.api.model.ApiArticle;
import de.schulte.smartbar.backoffice.api.model.ApiCategory;
import de.schulte.smartbar.backoffice.api.model.ApiMenu;
import de.schulte.smartbar.orderclient.api.model.LoginResponseBody;
import de.schulte.smartbar.orderclient.api.model.MenuCategory;
import de.schulte.smartbar.orderclient.api.model.MenuItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface MenuMapper {

    @Mapping(source = "categories", target = "categories", qualifiedByName = "mapCategories")
    LoginResponseBody mapToLoginResonse(ApiMenu apiMenu);

    @Named("mapCategories")
    default List<MenuCategory> mapCategories(final List<ApiCategory> categories) {
        return categories.stream().map(this::mapFromCategory).toList();
    }

    @Mapping(source = "articles", target = "items", qualifiedByName = "mapArticles")
    MenuCategory mapFromCategory(final ApiCategory apiCategory);

    @Named("mapArticles")
    default List<MenuItem> mapArticles(final List<ApiArticle> articles) {
        return articles.stream().map(this::mapFromArticles).toList();
    }

    @Mapping(source = "name", target = "articleName")
    @Mapping(source = "price", target = "articlePrice")
    MenuItem mapFromArticles(final ApiArticle apiArticle);


}
