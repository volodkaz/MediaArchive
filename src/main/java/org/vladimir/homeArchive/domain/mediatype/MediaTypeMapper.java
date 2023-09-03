package org.vladimir.homeArchive.domain.mediatype;

import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.vladimir.homeArchive.domain.DateMapper;
import org.vladimir.homeArchive.domain.MapperIntf;
import org.vladimir.homeArchive.model.object.MediaType;
import org.vladimir.homeArchive.model.entity.MediaTypeEntity;
import org.vladimir.homeArchive.model.request.MediaTypeRequest;
import org.vladimir.homeArchive.model.response.MediaTypeResponse;

import java.util.List;

@Mapper(componentModel = "spring", uses = DateMapper.class)
public interface MediaTypeMapper extends MapperIntf<MediaType, MediaTypeEntity, MediaTypeResponse, MediaTypeRequest> {
    MediaTypeRequest objectToRequest(MediaType mediaType);
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "modifyDate", ignore = true)
    MediaType requestToObject(MediaTypeRequest request);
    @Mapping(target = "id", source = "request.id")
    @Mapping(target = "createDate", source = "mediaType.createDate")
    @Mapping(target = "modifyDate", expression = "java(new java.util.Date())")
    @Mapping(target = "name", source = "request.name", defaultExpression = "java(mediaType.name())")
    @Mapping(target = "pathToMedia", source = "request.pathToMedia", defaultExpression = "java(mediaType.pathToMedia())")
    MediaTypeEntity requestToEntityForUpdate(MediaTypeRequest request, MediaType mediaType);

    MediaTypeResponse objectToResponse(MediaType mediaType);

    List<MediaTypeResponse> objectsToResponse(List<MediaType> allMediaType);
}
