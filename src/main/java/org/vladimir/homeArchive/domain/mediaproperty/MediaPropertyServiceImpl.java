package org.vladimir.homeArchive.domain.mediaproperty;

import org.springframework.stereotype.Service;
import org.vladimir.homeArchive.domain.AbstractService;
import org.vladimir.homeArchive.model.entity.MediaPropertyEntity;
import org.vladimir.homeArchive.model.object.MediaProperty;
import org.vladimir.homeArchive.model.request.MediaPropertyRequest;
import org.vladimir.homeArchive.model.response.MediaPropertyResponse;
import org.vladimir.homeArchive.repository.MediaPropertyRepository;

import java.util.Date;

@Service
//@RequiredArgsConstructor
public class MediaPropertyServiceImpl extends AbstractService <MediaProperty, MediaPropertyEntity, MediaPropertyResponse, MediaPropertyRequest>
        implements MediaPropertyService {
    private final MediaPropertyRepository repository;
    private final MediaPropertyMapper mapper;

    public MediaPropertyServiceImpl(MediaPropertyRepository repository,
                                    MediaPropertyMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void addObject(MediaPropertyRequest request) {
        MediaPropertyEntity entity = mapper.requestToEntity(request);
        entity.setId(null);
        entity.setCreateDate(new Date());
        repository.save(entity);
    }

    @Override
    public void updateObject(MediaPropertyRequest request) {
        MediaProperty property = getObjectById(request.id());
        if(!property.equals(request)){
            repository.save(mapper.requestToEntityForUpdate(request, property));
        }
    }


//    public MediaProperty getPropertyById(Long id) {
//        final MediaPropertyEntity entity = repository.findById(id)
//                .orElseThrow(() -> new NotFoundException("Не найден тег с id " + id));
//        return mapper.entityToMediaProperty(entity);
//    }
//
//    public List<MediaProperty> getAllProperties() {
//        final Iterable<MediaPropertyEntity> entities = repository.findAll();
//        List<MediaProperty> mediaProperties = new ArrayList<>();
//        for (MediaPropertyEntity entity : entities) {
//            mediaProperties.add(mapper.entityToMediaProperty(entity));
//        }
//        return mediaProperties;
//    }
//
//    @Override
//    public MediaPropertyResponse getMediaPropertyResponseById(Long id) {
//        final MediaProperty property = getPropertyById(id);
//        return mapper.objectToResponse(property);
//    }
//
//    @Override
//    public List<MediaPropertyResponse> getAllMediaPropertyResponse() {
//        return mapper.objectsToResponse(getAllProperties());
//    }
//
//    @Override
//    public void addMediaProperty(MediaPropertyRequest request) {
//        MediaPropertyEntity entity = mapper.requestToEntity(request);
//        entity.setId(null);
//        entity.setCreateDate(new Date());
//        repository.save(entity);
//    }
//
//    @Override
//    public void updateMediaProperty(MediaPropertyRequest request) {
//        MediaProperty property = getPropertyById(request.id());
//        if(!property.equals(request)){
//            repository.save(mapper.requestToEntityForUpdate(request, property));
//        }
//    }
//
//    @Override
//    public void deleteMediaProperty(Long id) {
//        repository.deleteById(id);
//    }
}
