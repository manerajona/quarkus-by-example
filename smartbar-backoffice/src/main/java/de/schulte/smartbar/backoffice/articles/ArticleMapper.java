package de.schulte.smartbar.backoffice.articles;

import de.schulte.smartbar.backoffice.api.model.ApiArticle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface ArticleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "picture", target = "pictureBase64")
    void mapToArticle(ApiArticle apiArticle, @MappingTarget Article article);

    @Mapping(source = "pictureBase64", target = "picture")
    ApiArticle mapToApiArticle(Article article);

}
