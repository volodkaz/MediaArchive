package org.vladimir.homeArchive.repository;

import org.springframework.data.repository.CrudRepository;
import org.vladimir.homeArchive.model.entity.MediaDataEntity;

public interface MediaRepository extends CrudRepository<MediaDataEntity, Long> {
}
