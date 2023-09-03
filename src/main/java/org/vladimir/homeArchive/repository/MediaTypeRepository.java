package org.vladimir.homeArchive.repository;

import org.springframework.data.repository.CrudRepository;
import org.vladimir.homeArchive.model.entity.MediaTypeEntity;

public interface MediaTypeRepository extends CrudRepository<MediaTypeEntity, Long> {
}
