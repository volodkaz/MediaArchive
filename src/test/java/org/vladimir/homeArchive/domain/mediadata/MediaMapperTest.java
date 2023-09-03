package org.vladimir.homeArchive.domain.mediadata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.vladimir.homeArchive.config.SpringConfig;
import org.vladimir.homeArchive.domain.mediadata.config.AbstractDataGenerator;
import org.vladimir.homeArchive.domain.mediadata.config.MediaDataEntityGenerator;
import org.vladimir.homeArchive.domain.mediadata.config.MediaDataSpringConfig;
import org.vladimir.homeArchive.domain.mediaproperty.MediaPropertyMapper;
import org.vladimir.homeArchive.model.entity.MediaDataEntity;
import org.vladimir.homeArchive.model.entity.MediaDataPropertyEntity;
import org.vladimir.homeArchive.model.entity.MediaPropertyEntity;
import org.vladimir.homeArchive.model.entity.MediaPropertySettingsEntity;
import org.vladimir.homeArchive.model.entity.MediaTypeEntity;
import org.vladimir.homeArchive.model.object.MediaData;
import org.vladimir.homeArchive.model.object.MediaDataProperty;
import org.vladimir.homeArchive.model.object.MediaProperty;
import org.vladimir.homeArchive.model.object.MediaPropertySettings;
import org.vladimir.homeArchive.model.object.MediaType;
import org.vladimir.homeArchive.model.request.MediaDataRequest;
import org.vladimir.homeArchive.model.response.MediaDataResponse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MediaMapperTest extends MediaDataSpringConfig {

    @Test
    void entityToObject(){

        final MediaData mediaData = mapper.entityToObject(entity);
        assertAll("",
                () -> assertNotNull(mediaData),
                () -> assertInstanceOf(MediaData.class, mediaData),
                () -> assertNotNull(mediaData.mediaProperties()),
                () -> assertEquals(3, mediaData.mediaProperties().size()),
                () -> assertEquals(entity.getId(), mediaData.id()),
                () -> assertEquals(entity.getModifyDate(), dateMapper.asDate(mediaData.modifyDate())),
                () -> assertEquals(entity.getCreateDate(), dateMapper.asDate(mediaData.createDate())),
                () -> assertEquals(entity.getPath(), mediaData.path())
                );
    }
    @Test
    void entityToObjectNull(){
        final MediaData mediaData = mapper.entityToObject(null);
        assertNull(mediaData);
    }
    @Test
    void getMediaProperties() {

        final List<MediaDataPropertyEntity> result = mapper.getMediaProperties(mediaData);
        assertAll("",
                () -> assertNotNull(result),
                () -> assertEquals(3, result.size())
        );
        for (int i = 0; i < result.size(); i++) {
            MediaDataPropertyEntity mediaDataPropertyEntity = result.get(i);
            MediaDataProperty mediaDataProperty = mediaData.mediaProperties().get(i);
            assertAll("",
                    () -> assertNotNull(mediaDataPropertyEntity),
                    () -> assertEquals(mediaDataProperty.id(),mediaDataPropertyEntity.getId()),
                    () -> assertNull(mediaDataPropertyEntity.getMediaPropertySettings()),
                    () -> assertNull(mediaDataPropertyEntity.getData())
            );
        }
    }

    @Test
    void objectToResponse() {
        final MediaDataResponse mediaDataResponse = mapper.objectToResponse(mediaData);
        assertResponse(mediaDataResponse, mediaData);
    }

    private void assertResponse(MediaDataResponse mediaDataResponse, MediaData mediaData) {
        assertAll("",
                () -> assertNotNull(mediaDataResponse),
                () -> assertInstanceOf(MediaDataResponse.class, mediaDataResponse),
                () -> assertEquals(mediaData.id(), mediaDataResponse.id()),
                () -> assertEquals(mediaData.path(), mediaDataResponse.path()),
                () -> assertEquals(mediaData.mediaProperties().size(), mediaDataResponse.properties().size())
        );
    }

    @Test
    void objectToResponseNull() {
        final MediaDataResponse mediaDataResponse = mapper.objectToResponse(null);
        assertNull(mediaDataResponse);
    }
    @Test
    void objectsToResponse(){
        final List<MediaDataResponse> responses = mapper.objectsToResponse(mediaDataList);
        assertAll("",
                () -> assertNotNull(responses),
                () -> assertEquals(mediaDataList.size(), responses.size()));
        for (int i = 0; i < responses.size(); i++) {
            final MediaDataResponse response = responses.get(i);
            final MediaData mediaData = mediaDataList.get(i);
            assertResponse(response, mediaData);
        }
    }
    @Test
    void objectsToResponseNull(){
        final List<MediaDataResponse> responses = mapper.objectsToResponse(null);
        assertNull(responses);
    }

    @Test
    void requestToEntity() {
        final MediaDataEntity entity = mapper.requestToEntity(request);
        assertAll("",
                () -> assertNotNull(entity),
                () -> assertInstanceOf(MediaDataEntity.class, entity),
                () -> assertEquals(request.id(), entity.getId()),
                () -> assertEquals(request.path(), entity.getPath()),
                () -> assertEquals(request.mediaProperties().size(), entity.getMediaProperties().size()),
                () -> assertNull(entity.getCreateDate()),
                () -> assertNull(entity.getModifyDate())
        );
    }

    @Test
    void requestToEntityNull() {
        final MediaDataEntity entity = mapper.requestToEntity(null);
        assertNull(entity);
    }
    @Test
    void requestToEntityForUpdate() {
        final MediaDataEntity entity = mapper.requestToEntityForUpdate(request, mediaData);
        assertAll("",
                () -> assertNotNull(entity),
                () -> assertInstanceOf(MediaDataEntity.class, entity),
                () -> assertEquals(request.id(), entity.getId()),
                () -> assertEquals(request.path(), entity.getPath()),
                () -> assertEquals(request.mediaProperties().size(), entity.getMediaProperties().size()),
                () -> assertEquals(mediaData.createDate(), dateMapper.asString(entity.getCreateDate())),
                () -> assertNotNull(entity.getModifyDate())
        );
    }

    @Test
    void requestToEntityForUpdateNull() {
        final MediaDataEntity entity = mapper.requestToEntityForUpdate(null, null);
        assertNull(entity);
    }
}