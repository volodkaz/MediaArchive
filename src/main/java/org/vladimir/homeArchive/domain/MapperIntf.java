package org.vladimir.homeArchive.domain;

import org.vladimir.homeArchive.model.response.MediaPropertyResponse;

import java.util.List;

public interface MapperIntf<T, E, R, Q>{
    T entityToObject(E entity);
    E objectToEntity(T object);
    R objectToResponse(T property);

    List<R> objectsToResponse(List<T> allObjects);

    E requestToEntityForUpdate(Q request, T mediaType);
}
