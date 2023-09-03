package org.vladimir.homeArchive.domain.mediatype;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.vladimir.homeArchive.config.SpringConfig;
import org.vladimir.homeArchive.domain.DateMapper;
import org.vladimir.homeArchive.model.entity.MediaTypeEntity;
import org.vladimir.homeArchive.model.object.MediaType;
import org.vladimir.homeArchive.model.request.MediaTypeRequest;
import org.vladimir.homeArchive.model.response.MediaTypeResponse;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class MediaTypeMapperTest extends SpringConfig{

    @Autowired
    private MediaTypeMapper mapper;
    @Autowired
    private DateMapper dateMapper;
    private MediaType mediaType;
    private MediaTypeRequest request;

    @BeforeEach
    public void init(){
        mediaType = new MediaType(1L, "mediaName", "mediaPath", "10-03-2022", "13-03-2023");
        request = new MediaTypeRequest(1L, "name", "path");
    }
    @Test
    @DisplayName("objectToRequest")
    void objectToRequest() {
        final MediaTypeRequest mediaTypeRequest = mapper.objectToRequest(mediaType);
        Assertions.assertAll("objectToRequestOk",
                () -> assertNotNull(mediaTypeRequest),
                () -> assertEquals(mediaType.id(), mediaTypeRequest.id()),
                () -> assertEquals(mediaType.name(), mediaTypeRequest.name()),
                () -> assertEquals(mediaType.pathToMedia(), mediaTypeRequest.pathToMedia())
        );
        Assertions.assertEquals(mediaTypeRequest.getClass().getDeclaredFields().length, 3);
    }

    @Test
    @DisplayName("objectToRequestNull")
    void objectToRequestNull() {
        final MediaTypeRequest mediaTypeRequest = mapper.objectToRequest(null);
        Assertions.assertNull(mediaTypeRequest);
    }

    @Test
    @DisplayName("requestToObject")
    void requestToObject() {
        final MediaType mediaType = mapper.requestToObject(request);
        Assertions.assertAll("requestToObjectOk",
                () -> assertNotNull(mediaType),
                () -> assertEquals(request.id(), mediaType.id()),
                () -> assertEquals(request.name(), mediaType.name()),
                () -> assertEquals(request.pathToMedia(), mediaType.pathToMedia()),
                () -> assertNull(mediaType.createDate()),
                () -> assertNull(mediaType.modifyDate())
        );
        Assertions.assertEquals(mediaType.getClass().getDeclaredFields().length, 5);
    }

    @Test
    @DisplayName("requestToObjectNull")
    void requestToObjectNull() {
        final MediaType mediaType = mapper.requestToObject(null);
        Assertions.assertNull(mediaType);
    }

    @Test

    void requestToEntityForUpdate() {
        final MediaTypeEntity entity = mapper.requestToEntityForUpdate(request, mediaType);
        Assertions.assertAll("requestToEntityForUpdate",
                () -> assertNotNull(entity),
                () -> assertEquals(request.id(), entity.getId()),
                () -> assertEquals(request.name(), entity.getName()),
                () -> assertEquals(request.pathToMedia(), entity.getPathToMedia()),
                () -> assertNotNull(entity.getCreateDate()),
                () -> assertNotNull(entity.getModifyDate()),
                () -> assertEquals(mediaType.id(), entity.getId()),
                () -> assertNotEquals(mediaType.name(), entity.getName()),
                () -> assertNotEquals(mediaType.pathToMedia(), entity.getPathToMedia()),
                () -> assertInstanceOf(MediaTypeEntity.class, entity)
                );
    }
    @Test
    void requestToEntityForUpdateNull() {
        final MediaTypeEntity entity = mapper.requestToEntityForUpdate(null, null);
        Assertions.assertNull(entity);
    }

    @Test
    void objectToResponse() {
        final MediaTypeResponse response = mapper.objectToResponse(mediaType);
        assertObject(mediaType, response);
    }

    private void assertObject(MediaType mediaType, MediaTypeResponse response) {
        Assertions.assertAll("objectToResponse",
                () -> assertNotNull(response),
                () -> assertEquals(mediaType.id(), response.id()),
                () -> assertEquals(mediaType.name(), response.name()),
                () -> assertEquals(mediaType.pathToMedia(), response.pathToMedia())
                );
        Assertions.assertEquals(MediaTypeResponse.class.getDeclaredFields().length, 3);
    }

    @Test
    void objectsToResponse() {
        final List<MediaType> mediaTypes = List.of(
                new MediaType(1L, "name1", "path1", null, null),
                new MediaType(2L, "name2", "path2", null, null),
                new MediaType(3L, "name3", "path3", null, null)
                );
        final List<MediaTypeResponse> mediaTypeResponses = mapper.objectsToResponse(mediaTypes);
        Assertions.assertEquals(3, mediaTypeResponses.size());
        for (int i = 0; i < mediaTypeResponses.size(); i++) {
            final MediaTypeResponse mediaTypeResponse = mediaTypeResponses.get(i);
            final MediaType mediaType = mediaTypes.get(i);
            assertObject(mediaType, mediaTypeResponse);
        }
    }

    @Test
    void objectsToResponseNull() {
        final List<MediaTypeResponse> mediaTypeResponses = mapper.objectsToResponse(null);
        Assertions.assertNull(mediaTypeResponses);

    }

    @Test
    void objectsToResponseEmpty() {
        List<MediaType> mediaTypes = Collections.emptyList();
        final List<MediaTypeResponse> mediaTypeResponses = mapper.objectsToResponse(mediaTypes);
        Assertions.assertEquals(mediaTypeResponses.size(), 0);
    }

    @Test
    void entityToObject(){
        MediaTypeEntity entity = new MediaTypeEntity(1L, "name", "path",
                dateMapper.asDate("11-03-2023"), dateMapper.asDate("22-05-2023"));
        final MediaType mediaTypeResult = mapper.entityToObject(entity);
        Assertions.assertAll("",
                () -> assertNotNull(mediaTypeResult),
                () -> assertInstanceOf(MediaType.class, mediaTypeResult),
                () -> assertEquals(entity.getId(), mediaTypeResult.id()),
                () -> assertEquals(entity.getName(), mediaTypeResult.name()),
                () -> assertEquals(entity.getPathToMedia(), mediaTypeResult.pathToMedia()),
                () -> assertEquals(entity.getCreateDate(), dateMapper.asDate(mediaTypeResult.createDate())),
                () -> assertEquals(entity.getModifyDate(), dateMapper.asDate(mediaTypeResult.modifyDate()))

        );
    }
}