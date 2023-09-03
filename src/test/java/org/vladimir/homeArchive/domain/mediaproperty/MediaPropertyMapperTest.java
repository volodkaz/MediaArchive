package org.vladimir.homeArchive.domain.mediaproperty;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.vladimir.homeArchive.config.SpringConfig;
import org.vladimir.homeArchive.model.entity.MediaPropertyEntity;
import org.vladimir.homeArchive.model.object.MediaProperty;
import org.vladimir.homeArchive.model.request.MediaPropertyRequest;
import org.vladimir.homeArchive.model.response.MediaPropertyResponse;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MediaPropertyMapperTest extends SpringConfig {

    @Autowired
    private MediaPropertyMapper mapper;


    @Test
    void entityToObject(){
        final MediaPropertyEntity entity = new MediaPropertyEntity(1L, "name", dateMapper.asDate("11-11-2022"),
                dateMapper.asDate("12-12-2022"));
        final MediaProperty property = mapper.entityToObject(entity);
        assertAll("",
                () -> assertNotNull(property),
                () -> assertInstanceOf(MediaProperty.class, property),
                () -> assertEquals(entity.getId(), property.id()),
                () -> assertEquals(entity.getName(), property.name()),
                () -> assertEquals(entity.getCreateDate(), dateMapper.asDate(property.createDate())),
                () -> assertEquals(entity.getModifyDate(), dateMapper.asDate(property.modifyDate()))
                );
    }
    @Test
    void entityToObjectNull(){
        final MediaProperty property = mapper.entityToObject(null);
        assertNull(property);
    }
    @Test
    void objectToResponse(){
        final MediaProperty property = new MediaProperty(1L, "name", "11-11-2022", "12-12-2023");
        final MediaPropertyResponse response = mapper.objectToResponse(property);
        assertObject(property, response);
    }

    private void assertObject(MediaProperty property, MediaPropertyResponse response) {
        assertAll("",
                () -> assertNotNull(response),
                () -> assertInstanceOf(MediaPropertyResponse.class, response),
                () -> assertEquals(property.id(), response.id()),
                () -> assertEquals(property.name(), response.name())
                );
    }

    @Test
    void objectToResponseNull(){
        final MediaPropertyResponse response = mapper.objectToResponse(null);
        assertNull(response);
    }
    @Test
    void objectsToResponse(){
        List<MediaProperty> properties = List.of(
                new MediaProperty(1L, "name1", "11-11-2022", "12-12-2023"),
                new MediaProperty(2L, "name2", "12-11-2022", "13-12-2023"),
                new MediaProperty(3L, "name3", "13-11-2022", "14-12-2023")
        );
        final List<MediaPropertyResponse> responses = mapper.objectsToResponse(properties);
        assertAll("",
                () -> assertNotNull(responses),
                () -> assertEquals(3, responses.size()));
        for (int i = 0; i < responses.size(); i++) {
            MediaPropertyResponse response = responses.get(i);
            MediaProperty property = properties.get(i);
            assertObject(property, response);
        }
    }
    @Test
    void objectsToResponseEmptyNull(){
        final List<MediaPropertyResponse> responses = mapper.objectsToResponse(Collections.emptyList());
        assertAll("",
                () -> assertNotNull(responses),
                () -> assertEquals(0, responses.size()));
        final List<MediaPropertyResponse> responses1 = mapper.objectsToResponse(null);
        assertNull(responses1);
    }
    @Test
    void requestToObject() {
        final MediaPropertyRequest request = new MediaPropertyRequest(1L, "name");
        final MediaProperty property = mapper.requestToObject(request);
        assertAll("",
                () -> assertNotNull(property),
                () -> assertInstanceOf(MediaProperty.class, property),
                () -> assertEquals(request.id(), property.id()),
                () -> assertEquals(request.name(), property.name()),
                () -> assertNull(property.createDate()),
                () -> assertNull(property.modifyDate())
                );
    }
    @Test
    void requestToObjectNull() {
        final MediaProperty property = mapper.requestToObject(null);
        assertNull(property);
    }
    @Test
    void requestToEntity() {
        final MediaPropertyRequest request = new MediaPropertyRequest(1L, "name");
        final MediaPropertyEntity entity = mapper.requestToEntity(request);
        assertAll("",
                () -> assertNotNull(entity),
                () -> assertInstanceOf(MediaPropertyEntity.class, entity),
                () -> assertEquals(request.id(), entity.getId()),
                () -> assertEquals(request.name(), entity.getName()),
                () -> assertNull(entity.getCreateDate()),
                () -> assertNull(entity.getModifyDate())
                );
    }
    @Test
    void requestToEntityNull() {
        final MediaPropertyEntity entity = mapper.requestToEntity(null);
        assertNull(entity);
    }
    @Test
    void requestToEntityForUpdate() {
        final MediaPropertyRequest request = new MediaPropertyRequest(1L, "name");
        final MediaProperty property = new MediaProperty(1L, "name1", "11-11-2022", "12-12-2023");
        final MediaPropertyEntity entity = mapper.requestToEntityForUpdate(request, property);
        assertAll("",
                () -> assertNotNull(entity),
                () -> assertInstanceOf(MediaPropertyEntity.class, entity),
                () -> assertEquals(request.id(), entity.getId()),
                () -> assertEquals(request.name(), entity.getName()),
                () -> assertEquals(dateMapper.asDate(property.createDate()), entity.getCreateDate()),
                () -> assertNotNull(entity.getModifyDate())
                );
    }
    @Test
    void requestToEntityForUpdateNull() {
        final MediaPropertyEntity entity = mapper.requestToEntityForUpdate(null, null);
        assertNull(entity);
    }
}