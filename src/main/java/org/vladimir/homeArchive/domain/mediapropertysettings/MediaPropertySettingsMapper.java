package org.vladimir.homeArchive.domain.mediapropertysettings;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vladimir.homeArchive.domain.DateMapper;
import org.vladimir.homeArchive.domain.MapperIntf;
import org.vladimir.homeArchive.domain.mediaproperty.MediaPropertyMapper;
import org.vladimir.homeArchive.domain.mediatype.MediaTypeMapper;
import org.vladimir.homeArchive.model.entity.MediaPropertySettingsEntity;
import org.vladimir.homeArchive.model.object.MediaPropertySettings;
import org.vladimir.homeArchive.model.request.MediaPropertySettingRequest;
import org.vladimir.homeArchive.model.response.MediaPropertySettingResponse;

@Mapper(componentModel = "spring", uses = {MediaTypeMapper.class, MediaPropertyMapper.class, DateMapper.class})
public interface MediaPropertySettingsMapper
        extends MapperIntf<MediaPropertySettings, MediaPropertySettingsEntity, MediaPropertySettingResponse, MediaPropertySettingRequest> {

    @Mapping(target = "mediaType", expression = "java(new org.vladimir.homeArchive.model.entity.MediaTypeEntity(request.typeId()))")
    @Mapping(target = "mediaProperty", expression = "java(new org.vladimir.homeArchive.model.entity.MediaPropertyEntity(request.propertyId()))")
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "modifyDate", ignore = true)
    MediaPropertySettingsEntity requestToEntity(MediaPropertySettingRequest request);

    @Mapping(target = "id", source = "request.id")
    @Mapping(target = "mediaType", expression = "java(new org.vladimir.homeArchive.model.entity.MediaTypeEntity(request.typeId()))")
    @Mapping(target = "mediaProperty", expression = "java(new org.vladimir.homeArchive.model.entity.MediaPropertyEntity(request.propertyId()))")
    @Mapping(target = "createDate", source = "mediaPropertySettings.createDate")
    @Mapping(target = "modifyDate", expression = "java(new java.util.Date())")
    MediaPropertySettingsEntity requestToEntityForUpdate(MediaPropertySettingRequest request,
                                                         MediaPropertySettings mediaPropertySettings);
}
