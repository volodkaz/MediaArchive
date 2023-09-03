package org.vladimir.homeArchive.domain.mediapropertysettings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.vladimir.homeArchive.config.SpringConfig;
import org.vladimir.homeArchive.domain.DateMapper;
import org.vladimir.homeArchive.domain.mediaproperty.MediaPropertyMapper;
import org.vladimir.homeArchive.domain.mediatype.MediaTypeMapper;
import org.vladimir.homeArchive.model.entity.MediaPropertyEntity;
import org.vladimir.homeArchive.model.entity.MediaPropertySettingsEntity;
import org.vladimir.homeArchive.model.entity.MediaTypeEntity;
import org.vladimir.homeArchive.model.object.MediaProperty;
import org.vladimir.homeArchive.model.object.MediaPropertySettings;
import org.vladimir.homeArchive.model.object.MediaType;
import org.vladimir.homeArchive.model.request.MediaPropertySettingRequest;
import org.vladimir.homeArchive.model.response.MediaPropertyResponse;
import org.vladimir.homeArchive.model.response.MediaPropertySettingResponse;
import org.vladimir.homeArchive.model.response.MediaTypeResponse;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MediaPropertySettingsMapperTest extends SpringConfig {

    @Autowired
    private MediaPropertySettingsMapper mapper;
    @Autowired
    private MediaTypeMapper mediaTypeMapper;
    @Autowired
    private MediaPropertyMapper mediaPropertyMapper;

    @BeforeEach
    public void init(){

    }
    @Test
    void entityToObject(){
        final MediaPropertyEntity propertyEntity = new MediaPropertyEntity(1L, "propertyName",
                dateMapper.asDate("11-11-2022"), dateMapper.asDate("12-09-2023"));
        final MediaTypeEntity mediaTypeEntity = new MediaTypeEntity(1L, "typeName", "path", dateMapper.asDate("12-12-2022"),
                dateMapper.asDate("10-09-2023"));
        final MediaPropertySettingsEntity entity = new MediaPropertySettingsEntity(1L,
                mediaTypeEntity, propertyEntity, dateMapper.asDate("13-12-2022"),
                dateMapper.asDate("13-10-2023"));
        final MediaPropertySettings mediaPropertySettings = mapper.entityToObject(entity);
        final MediaType mediaType = mediaTypeMapper.entityToObject(mediaTypeEntity);
        final MediaProperty mediaProperty = mediaPropertyMapper.entityToObject(propertyEntity);
        Assertions.assertAll("entityToObjectOk",
                () -> assertNotNull(mediaPropertySettings),
                () -> assertInstanceOf(MediaPropertySettings.class, mediaPropertySettings),
                () -> assertEquals(entity.getId(), mediaPropertySettings.id()),
                () -> assertEquals(mediaType, mediaPropertySettings.mediaType()),
                () -> assertEquals(mediaProperty, mediaPropertySettings.mediaProperty()),
                () -> assertEquals(entity.getCreateDate(), dateMapper.asDate(mediaPropertySettings.createDate())),
                () -> assertEquals(entity.getModifyDate(), dateMapper.asDate(mediaPropertySettings.modifyDate()))
                );
    }

    @Test
    void entityToObjectNull(){
        final MediaPropertySettings mediaPropertySettings = mapper.entityToObject(null);
        Assertions.assertNull(mediaPropertySettings);
    }
    @Test
    void objectToResponse(){
        final MediaType mediaType = new MediaType(1L, "typeName", "path", "12-12-2022",
                "10-09-2023");
        final MediaProperty property = new MediaProperty(1L, "propertyName", "11-11-2022", "12-09-2023");
        final MediaPropertySettings mediaPropertySettings = new MediaPropertySettings(1L, mediaType, property,
                "12-12-2022", "10-09-2023");
        final MediaPropertySettingResponse response = mapper.objectToResponse(mediaPropertySettings);

        assertObject(mediaPropertySettings, response);
    }

    @Test
    void objectToResponseNull(){
        final MediaPropertySettingResponse response = mapper.objectToResponse(null);
        Assertions.assertNull(response);
    }

    @Test
    void objectsToResponse(){
        List<MediaPropertySettings> propertySettings = List.of(
                new MediaPropertySettings(1L, null, null, "11-11-2022", "12-09-2023"),
                new MediaPropertySettings(2L, null, null, "12-11-2022", "13-09-2023"),
                new MediaPropertySettings(3L, null, null, "13-11-2022", "14-09-2023"));

        final List<MediaPropertySettingResponse> responses = mapper
                .objectsToResponse(propertySettings);

        Assertions.assertAll("",
                () -> assertNotNull(responses),
                () -> assertEquals(3, responses.size()));

        for (int i = 0; i < responses.size(); i++) {
            final MediaPropertySettingResponse response = responses.get(i);
            final MediaPropertySettings mediaPropertySettings = propertySettings.get(i);
            assertObject(mediaPropertySettings, response);
        }
    }

    @Test
    void objectsToResponseEmptyOrNull(){
        final List<MediaPropertySettings> propertySettings = Collections.emptyList();
        final List<MediaPropertySettingResponse> responses = mapper.objectsToResponse(propertySettings);
        assertAll("empty",
                () -> assertNotNull(responses),
                () -> assertEquals(0, responses.size())
        );
        final List<MediaPropertySettingResponse> responses1 = mapper.objectsToResponse(null);
        assertNull(responses1);

    }
    private void assertObject(MediaPropertySettings mediaPropertySettings, MediaPropertySettingResponse response) {
        final MediaTypeResponse mediaTypeResponse = mediaTypeMapper.objectToResponse(mediaPropertySettings.mediaType());
        final MediaPropertyResponse mediaPropertyResponse = mediaPropertyMapper.objectToResponse(mediaPropertySettings.mediaProperty());
        Assertions.assertAll("",
                () -> assertInstanceOf(MediaPropertySettingResponse.class, response),
                () -> assertEquals(mediaPropertySettings.id(), response.id()),
                () -> assertEquals(mediaTypeResponse, response.mediaType()),
                () -> assertEquals(mediaPropertyResponse, response.mediaProperty())
        );
    }

    @Test
    void requestToEntity() {
        final MediaPropertySettingRequest request = new MediaPropertySettingRequest(1L, 2L, 3L);
        final MediaPropertySettingsEntity entity = mapper.requestToEntity(request);
        Assertions.assertAll("",
                () -> assertNotNull(entity),
                () -> assertInstanceOf(MediaPropertySettingsEntity.class, entity),
                () -> assertEquals(request.id(), entity.getId()),
                () -> assertEquals(request.typeId(), entity.getMediaType().getId()),
                () -> assertEquals(request.propertyId(), entity.getMediaProperty().getId()),
                () -> assertNull(entity.getCreateDate()),
                () -> assertNull(entity.getModifyDate())
                );
    }

    @Test
    void requestToEntityNull() {
        final MediaPropertySettingsEntity entity = mapper.requestToEntity(null);
        assertNull(entity);
    }
    @Test
    void requestToEntityForUpdate() {
        final MediaPropertySettingRequest request = new MediaPropertySettingRequest(1L, 2L, 3L);
        final MediaType mediaType = new MediaType(1L, "typeName", "path", "12-12-2022",
                "10-09-2023");
        final MediaProperty property = new MediaProperty(1L, "propertyName", "11-11-2022", "12-09-2023");
        final MediaPropertySettings mediaPropertySettings = new MediaPropertySettings(1L, mediaType, property,
                "11-11-2022", "12-12-2023");
        final MediaPropertySettingsEntity entity = mapper.requestToEntityForUpdate(request, mediaPropertySettings);

        assertAll("",
                () -> assertNotNull(entity),
                () -> assertInstanceOf(MediaPropertySettingsEntity.class, entity),
                () -> assertEquals(request.id(), entity.getId()),
                () -> assertEquals(request.typeId(), entity.getMediaType().getId()),
                () -> assertEquals(request.propertyId(), entity.getMediaProperty().getId()),
                () -> assertEquals(dateMapper.asDate(mediaPropertySettings.createDate()), entity.getCreateDate()),
                () -> assertNotNull(entity.getModifyDate())
                );
    }

    @Test
    void requestToEntityForUpdateNull() {
        final MediaPropertySettingsEntity entity = mapper.requestToEntityForUpdate(null, null);
        assertNull(entity);
    }
}