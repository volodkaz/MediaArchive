package org.vladimir.homeArchive.domain.mediaproperty;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vladimir.homeArchive.domain.DateMapper;
import org.vladimir.homeArchive.domain.MapperIntf;
import org.vladimir.homeArchive.model.entity.MediaPropertyEntity;
import org.vladimir.homeArchive.model.object.MediaProperty;
import org.vladimir.homeArchive.model.request.MediaPropertyRequest;
import org.vladimir.homeArchive.model.response.MediaPropertyResponse;

@Mapper(componentModel = "spring", uses = DateMapper.class)
public interface MediaPropertyMapper
        extends MapperIntf<MediaProperty, MediaPropertyEntity, MediaPropertyResponse, MediaPropertyRequest> {

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "modifyDate", ignore = true)
    MediaProperty requestToObject(MediaPropertyRequest request);
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "modifyDate", ignore = true)
    MediaPropertyEntity requestToEntity(MediaPropertyRequest request);

    @Mapping(target = "id", source = "request.id")
    @Mapping(target = "createDate", source = "property.createDate")
    @Mapping(target = "modifyDate", expression = "java(new java.util.Date())")
    @Mapping(target = "name", source = "request.name", defaultExpression = "java(property.name())")
    MediaPropertyEntity requestToEntityForUpdate(MediaPropertyRequest request, MediaProperty property);
}
