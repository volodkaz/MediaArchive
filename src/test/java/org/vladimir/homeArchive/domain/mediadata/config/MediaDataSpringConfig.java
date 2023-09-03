package org.vladimir.homeArchive.domain.mediadata.config;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.vladimir.homeArchive.domain.DateMapper;
import org.vladimir.homeArchive.domain.mediadata.MediaMapper;
import org.vladimir.homeArchive.model.entity.MediaDataEntity;
import org.vladimir.homeArchive.model.object.MediaData;
import org.vladimir.homeArchive.model.object.MediaDataProperty;
import org.vladimir.homeArchive.model.request.MediaDataRequest;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("org.vladimir.homeArchive.domain")
@SpringBootTest
public class MediaDataSpringConfig {
    @Autowired
    protected DateMapper dateMapper;
    @Autowired
    protected MediaMapper mapper;
    @Autowired
    @Qualifier("mediaDataGenerator")
    protected AbstractDataGenerator mediaDataGenerator;
    @Autowired
    @Qualifier("mediaDataEntityGenerator")
    protected AbstractDataGenerator mediaDataEntityGenerator;
    @Autowired
    @Qualifier("mediaDataRequestGenerator")
    protected AbstractDataGenerator mediaDataRequestGenerator;

    protected MediaData mediaData;

    protected MediaDataEntity entity;

    protected MediaDataRequest request;

    protected List<MediaData> mediaDataList;

    @BeforeEach
    public void init(){
        List<MediaDataProperty> mediaProperties = new ArrayList<>(3);
        final String typePath = "typePath";
        final String typeName = "mediaName_";
        final String createDate = "11-03-2023";
        final String modifyDate = "12-04-2023";
        final String propertyName = "propertyName_";
        final String dataPr = "data_";
        final String path = "path";

        mediaData = (MediaData) mediaDataGenerator
                .getData(1L, typePath, typeName, createDate, modifyDate, propertyName, dataPr, path);
        entity = (MediaDataEntity) mediaDataEntityGenerator
                .getData(1L, typePath, typeName, createDate, modifyDate, propertyName, dataPr, path);
        request = (MediaDataRequest) mediaDataRequestGenerator
                .getData(1L,  typePath, typeName, createDate, modifyDate, propertyName, dataPr, path);
        mediaDataList = List.of(mediaData, (MediaData) mediaDataGenerator
                        .getData(2L, typePath + "_2", typeName + "_2",
                                createDate, modifyDate, propertyName + "_2", dataPr + "_2", path + "_2"),
                (MediaData) mediaDataGenerator
                        .getData(3L, typePath + "_3", typeName + "_3",
                                createDate, modifyDate, propertyName + "_3", dataPr + "_3", path + "_3")
        );
    }
}
