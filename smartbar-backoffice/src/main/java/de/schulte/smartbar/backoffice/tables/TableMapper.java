package de.schulte.smartbar.backoffice.tables;

import de.schulte.smartbar.backoffice.api.model.ApiTable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface TableMapper {

    @Mapping(target = "id", ignore = true)
    void mapToTable(ApiTable apiTable, @MappingTarget Table table);

    ApiTable mapToApiTable(Table table);

}
