package org.vladimir.homeArchive.repository;

import org.springframework.data.repository.CrudRepository;
import org.vladimir.homeArchive.model.entity.MediaPropertySettingsEntity;

public interface MediaPropertySettingRepository extends CrudRepository<MediaPropertySettingsEntity, Long> {
}
