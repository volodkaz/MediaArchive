package org.vladimir.homeArchive.domain.mediadata.config;

import org.springframework.stereotype.Component;
import org.vladimir.homeArchive.model.request.MediaDataPropertyRequest;
import org.vladimir.homeArchive.model.request.MediaDataRequest;
@Component
public class MediaDataRequestGenerator extends AbstractDataGenerator<MediaDataRequest, MediaDataPropertyRequest> {
    @Override
    public MediaDataRequest getData(Long id, String typePath, String typeName, String createDate, String modifyDate,
                                    String propertyName, String dataPr, String path) {
        return new MediaDataRequest(id, getProps(typePath, typeName, createDate, modifyDate, propertyName, dataPr), path);
    }

    @Override
    protected MediaDataPropertyRequest createProp(String typePath, String typeName, String createDate,
                                                  String modifyDate, String propertyName, String dataPr, int i,
                                                  Long l) {
        return new MediaDataPropertyRequest(l, l + 2, dataPr);
    }
}
