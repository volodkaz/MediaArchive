package org.vladimir.homeArchive.domain.mediadata;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.vladimir.homeArchive.domain.DateMapper;
import org.vladimir.homeArchive.domain.MapperIntf;
import org.vladimir.homeArchive.domain.mediapropertysettings.MediaPropertySettingsMapper;
import org.vladimir.homeArchive.model.entity.MediaDataEntity;
import org.vladimir.homeArchive.model.entity.MediaDataPropertyEntity;
import org.vladimir.homeArchive.model.object.MediaData;
import org.vladimir.homeArchive.model.object.MediaDataProperty;
import org.vladimir.homeArchive.model.request.MediaDataRequest;
import org.vladimir.homeArchive.model.response.MediaDataPropertyResponse;
import org.vladimir.homeArchive.model.response.MediaDataResponse;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {DateMapper.class, MediaPropertySettingsMapper.class, MediaDataPropertyMapper.class})
public interface MediaMapper extends MapperIntf<MediaData, MediaDataEntity, MediaDataResponse, MediaDataRequest> {

    @Override
    @Mapping(target = "id", source = "request.id")
    @Mapping(target = "path", source = "request.path")
    @Mapping(target = "createDate", source = "mediaData.createDate")
    @Mapping(target = "modifyDate", expression = "java(new java.util.Date())")
    @Mapping(target = "mediaProperties", qualifiedByName = "getMediaProperties", source = "mediaData")
    MediaDataEntity requestToEntityForUpdate(MediaDataRequest request, MediaData mediaData);

    @Named("getMediaProperties")
    default List<MediaDataPropertyEntity> getMediaProperties(MediaData mediaData) {
        final List<MediaDataPropertyEntity> mediaDataPropertyEntities = new ArrayList<>(mediaData.mediaProperties().size());
        for (MediaDataProperty property : mediaData.mediaProperties()) {
            mediaDataPropertyEntities.add(new MediaDataPropertyEntity(property.id()));
        }
        return mediaDataPropertyEntities;
    }

    @Override
    @Mapping(target = "properties" ,source = "mediaProperties")
    MediaDataResponse objectToResponse(MediaData property);

    //    @Override
//    default MediaDataResponse objectToResponse(MediaData property) {
//        final ArrayList<MediaDataPropertyResponse> mediaDataPropertyResponses = new ArrayList<>(property.mediaProperties().size());
//        for (MediaDataProperty mediaProperty : property.mediaProperties()) {
//            mediaDataPropertyResponses.add(new MediaDataPropertyResponse(mediaProperty.id(), mediaProperty.mediaPropertySettings().mediaType().name(),
//                    mediaProperty.mediaPropertySettings().mediaProperty().name(), mediaProperty.data()));
//        }
//        final MediaDataResponse mediaDataResponse = new MediaDataResponse(property.id(), mediaDataPropertyResponses, property.path());
//        return mediaDataResponse;
//    }

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "modifyDate", ignore = true)
    MediaDataEntity requestToEntity(MediaDataRequest request);
}
