package org.vladimir.homeArchive.domain.mediadata.config;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDataGenerator <T, P>{
    public static final int COUNT_OF_ELEMENT = 3;
    public abstract T getData(Long id, String typePath, String typeName, String createDate, String modifyDate,
                     String propertyName, String dataPr, String path);

    protected List<P> getProps(String typePath, String typeName, String createDate, String modifyDate,
                               String propertyName, String dataPr){
        final ArrayList<P> props = new ArrayList<>(COUNT_OF_ELEMENT);
        for (int i = 0; i < COUNT_OF_ELEMENT; i++) {
            final Long l = Long.valueOf(i);
            props.add(createProp(typePath, typeName, createDate, modifyDate, propertyName, dataPr, i, l));
        }
        return props;
    }

    protected abstract P createProp(String typePath, String typeName, String createDate, String modifyDate, String propertyName, String dataPr, int i, Long l);
}
