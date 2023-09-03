package org.vladimir.homeArchive.domain.mediadata.config;

import org.springframework.stereotype.Component;
import org.vladimir.homeArchive.model.object.MediaData;
import org.vladimir.homeArchive.model.object.MediaDataProperty;
import org.vladimir.homeArchive.model.object.MediaProperty;
import org.vladimir.homeArchive.model.object.MediaPropertySettings;
import org.vladimir.homeArchive.model.object.MediaType;
@Component
public class MediaDataGenerator extends AbstractDataGenerator<MediaData, MediaDataProperty> {
    @Override
    public MediaData getData(Long id, String typePath, String typeName, String createDate, String modifyDate,
                             String propertyName, String dataPr, String path) {
        return new MediaData(id, getProps(typePath, typeName, createDate, modifyDate, propertyName, dataPr), createDate, modifyDate, path);
    }

    @Override
    protected MediaDataProperty createProp(String typePath, String typeName, String createDate, String modifyDate,
                                           String propertyName, String dataPr, int i, Long l) {
        MediaType mediaType = new MediaType(l, typeName + i, typePath + i, createDate, modifyDate);
        MediaProperty mediaProperty = new MediaProperty(l, propertyName + i, createDate, modifyDate);
        MediaPropertySettings mediaPropertySetting = new MediaPropertySettings(l, mediaType, mediaProperty,
                createDate, modifyDate);

        final MediaDataProperty data = new MediaDataProperty(l, mediaPropertySetting, dataPr);
        return data;
    }
}
