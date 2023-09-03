package org.vladimir.homeArchive.domain.mediadata;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.vladimir.homeArchive.config.SpringConfig;
import org.vladimir.homeArchive.model.entity.MediaDataPropertyEntity;
import org.vladimir.homeArchive.model.request.MediaDataPropertyRequest;

import static org.junit.jupiter.api.Assertions.*;

class MediaDataPropertyMapperTest extends SpringConfig {

    @Autowired
    private MediaDataPropertyMapper mapper;
    @Test
    void requestToEntity() {
        final MediaDataPropertyRequest request = new MediaDataPropertyRequest(1L, 1L, "data");
        final MediaDataPropertyEntity entity = mapper.requestToEntity(request);

        assertAll("",
                () -> assertNotNull(entity),
                () -> assertInstanceOf(MediaDataPropertyEntity.class, entity),
                () -> assertEquals(request.id(), entity.getId()),
                () -> assertEquals(request.propertySettingId(), entity.getMediaPropertySettings().getId()),
                () -> assertEquals(request.data(), entity.getData())
                );
    }

    @Test
    void requestToEntityNull() {
        final MediaDataPropertyEntity entity = mapper.requestToEntity(null);
        assertNull(entity);
    }
}