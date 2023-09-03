package org.vladimir.homeArchive.domain.mediadata.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vladimir.homeArchive.domain.DateMapper;
import org.vladimir.homeArchive.model.entity.MediaDataEntity;
import org.vladimir.homeArchive.model.entity.MediaDataPropertyEntity;
import org.vladimir.homeArchive.model.entity.MediaPropertyEntity;
import org.vladimir.homeArchive.model.entity.MediaPropertySettingsEntity;
import org.vladimir.homeArchive.model.entity.MediaTypeEntity;
@Component
public class MediaDataEntityGenerator extends AbstractDataGenerator<MediaDataEntity, MediaDataPropertyEntity> {
    @Autowired
    private DateMapper dateMapper;
    @Override
    public MediaDataEntity getData(Long id, String typePath, String typeName, String createDate, String modifyDate,
                                   String propertyName, String dataPr, String path) {
        return new MediaDataEntity(id, getProps(typePath, typeName, createDate, modifyDate, propertyName, dataPr),
                dateMapper.asDate(createDate), dateMapper.asDate(modifyDate), path);
    }

    @Override
    protected MediaDataPropertyEntity createProp(String typePath, String typeName, String createDate, String modifyDate,
                                                 String propertyName, String dataPr, int i, Long l) {

        final MediaTypeEntity mediaTypeEntity = new MediaTypeEntity(l, typeName + i, typePath + i,
                dateMapper.asDate(createDate),dateMapper.asDate(modifyDate));
        final MediaPropertyEntity mediaPropertyEntity = new MediaPropertyEntity(l, propertyName + i,
                dateMapper.asDate(createDate), dateMapper.asDate(modifyDate));
        final MediaPropertySettingsEntity propertySettingsEntity = new MediaPropertySettingsEntity(l, mediaTypeEntity, mediaPropertyEntity,
                dateMapper.asDate(createDate), dateMapper.asDate(modifyDate));
        return new MediaDataPropertyEntity(
                l, propertySettingsEntity, "data_" + i);
    }
}
