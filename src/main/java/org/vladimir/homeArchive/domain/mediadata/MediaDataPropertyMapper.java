package org.vladimir.homeArchive.domain.mediadata;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vladimir.homeArchive.domain.MapperIntf;
import org.vladimir.homeArchive.model.entity.MediaDataPropertyEntity;
import org.vladimir.homeArchive.model.object.MediaDataProperty;
import org.vladimir.homeArchive.model.request.MediaDataPropertyRequest;
import org.vladimir.homeArchive.model.response.MediaDataPropertyResponse;

@Mapper(componentModel = "spring")
public interface MediaDataPropertyMapper extends
        MapperIntf<MediaDataProperty, MediaDataPropertyEntity, MediaDataPropertyResponse, MediaDataPropertyRequest> {

    @Mapping(target = "mediaPropertySettings", expression = "java(new org.vladimir.homeArchive.model.entity.MediaPropertySettingsEntity(request.propertySettingId()))")
//    @Mapping(target = "data", ignore = true)
    MediaDataPropertyEntity requestToEntity(MediaDataPropertyRequest request);

    @Override
    @Mapping(target = "id", source = "request.id")
    @Mapping(target = "data", source = "request.data")
    MediaDataPropertyEntity requestToEntityForUpdate(MediaDataPropertyRequest request, MediaDataProperty mediaType);
}
