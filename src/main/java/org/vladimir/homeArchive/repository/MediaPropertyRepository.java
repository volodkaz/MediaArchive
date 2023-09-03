package org.vladimir.homeArchive.repository;

import org.springframework.data.repository.CrudRepository;
import org.vladimir.homeArchive.model.entity.MediaPropertyEntity;

public interface MediaPropertyRepository extends CrudRepository<MediaPropertyEntity, Long> {
}
