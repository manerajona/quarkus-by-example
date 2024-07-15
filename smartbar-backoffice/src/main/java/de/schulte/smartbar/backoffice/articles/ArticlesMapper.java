package de.schulte.smartbar.backoffice.articles;

import de.schulte.smartbar.backoffice.api.model.ApiArticle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface ArticlesMapper {

    @Mapping(source = "pictureBase64", target = "picture")
    ApiArticle mapFrom(Article article);

}
