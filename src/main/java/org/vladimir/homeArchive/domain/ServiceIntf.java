package org.vladimir.homeArchive.domain;

import java.util.List;

public interface ServiceIntf<R, Q> {
    R getObjectResponseById(Long id);
    List<R> getAllObjectsResponse();
    void deleteObject(Long id);
    void addObject(Q request);
    void updateObject(Q request);
}
