package org.vladimir.homeArchive.domain.mediadata;

import org.springframework.stereotype.Service;
import org.vladimir.homeArchive.domain.AbstractService;
import org.vladimir.homeArchive.model.entity.MediaDataEntity;
import org.vladimir.homeArchive.model.entity.MediaDataPropertyEntity;
import org.vladimir.homeArchive.model.object.MediaData;
import org.vladimir.homeArchive.model.request.MediaDataRequest;
import org.vladimir.homeArchive.model.response.MediaDataResponse;
import org.vladimir.homeArchive.repository.MediaDataPropertyRepository;
import org.vladimir.homeArchive.repository.MediaRepository;

import java.util.Date;
import java.util.List;

@Service
//@RequiredArgsConstructor
public class MediaServiceImpl extends AbstractService<MediaData, MediaDataEntity, MediaDataResponse, MediaDataRequest>
        implements MediaService{
    private final MediaRepository repository;
    private final MediaDataPropertyRepository propertyRepository;
    private final MediaMapper mapper;

    public MediaServiceImpl(
            MediaRepository repository,
            MediaMapper mapper,
            MediaDataPropertyRepository propertyRepository) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public void addObject(MediaDataRequest request) {
        MediaDataEntity entity = mapper.requestToEntity(request);
        List<MediaDataPropertyEntity> propertyEntityList = entity.getMediaProperties();
        resetId(entity);
        propertyRepository.saveAll(propertyEntityList);
        entity.setCreateDate(new Date());
        repository.save(entity);
    }

    private void resetId(MediaDataEntity entity) {
        entity.setId(null);
        for (MediaDataPropertyEntity mediaProperty : entity.getMediaProperties()) {
            mediaProperty.setId(null);
        }
    }

    @Override
    public void updateObject(MediaDataRequest request) {
        final MediaData mediaData = getObjectById(request.id());
        final MediaDataEntity entity = mapper.requestToEntityForUpdate(request, mediaData);
        propertyRepository.saveAll(entity.getMediaProperties());
        repository.save(entity);
    }
}
