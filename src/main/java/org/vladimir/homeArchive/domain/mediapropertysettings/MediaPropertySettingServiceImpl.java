package org.vladimir.homeArchive.domain.mediapropertysettings;

import org.springframework.stereotype.Service;
import org.vladimir.homeArchive.domain.AbstractService;
import org.vladimir.homeArchive.model.entity.MediaPropertySettingsEntity;
import org.vladimir.homeArchive.model.object.MediaPropertySettings;
import org.vladimir.homeArchive.model.request.MediaPropertySettingRequest;
import org.vladimir.homeArchive.model.response.MediaPropertySettingResponse;
import org.vladimir.homeArchive.repository.MediaPropertySettingRepository;

import java.util.Date;

@Service
//@RequiredArgsConstructor
public class MediaPropertySettingServiceImpl
        extends AbstractService<MediaPropertySettings, MediaPropertySettingsEntity, MediaPropertySettingResponse, MediaPropertySettingRequest>
        implements MediaPropertySettingService {
    private final MediaPropertySettingRepository repository;
    private final MediaPropertySettingsMapper mapper;

    public MediaPropertySettingServiceImpl(
            MediaPropertySettingRepository repository,
            MediaPropertySettingsMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void addObject(MediaPropertySettingRequest request) {
        if(request.typeId() == null || request.propertyId() == null){
            throw new IllegalArgumentException("Не указаны необходимые параметры");
        }
        MediaPropertySettingsEntity entity = mapper.requestToEntity(request);
        entity.setId(null);
        entity.setCreateDate(new Date());

        repository.save(entity);
    }

    @Override
    public void updateObject(MediaPropertySettingRequest request) {
        if(request.id() == null || request.typeId() == null || request.propertyId() == null){
            throw new IllegalArgumentException("Не указаны необходимые параметры");
        }

        final MediaPropertySettings mediaPropertySettings = getObjectById(request.id());

        if(!mediaPropertySettings.mediaProperty().id().equals(request.propertyId())
                || !mediaPropertySettings.mediaType().id().equals(request.id())){
            final MediaPropertySettingsEntity entity = mapper.requestToEntityForUpdate(request, mediaPropertySettings);

            repository.save(entity);
        }
    }
}
