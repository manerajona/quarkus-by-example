package de.schulte.smartbar.backoffice.menu;

import de.schulte.smartbar.backoffice.api.model.ApiCategory;
import de.schulte.smartbar.backoffice.categories.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface CategoriesMapper {

    ApiCategory mapToApiCategory(Category category);

}
