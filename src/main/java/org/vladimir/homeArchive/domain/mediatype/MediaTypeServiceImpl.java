package org.vladimir.homeArchive.domain.mediatype;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.vladimir.homeArchive.domain.AbstractService;
import org.vladimir.homeArchive.domain.MapperIntf;
import org.vladimir.homeArchive.model.object.MediaType;
import org.vladimir.homeArchive.model.entity.MediaTypeEntity;
import org.vladimir.homeArchive.model.request.MediaTypeRequest;
import org.vladimir.homeArchive.model.response.MediaTypeResponse;
import org.vladimir.homeArchive.repository.MediaTypeRepository;

import java.util.Date;
import java.util.List;
@Service
//@RequiredArgsConstructor
public class MediaTypeServiceImpl
        extends AbstractService<MediaType, MediaTypeEntity, MediaTypeResponse, MediaTypeRequest>
        implements MediaTypeService {
    private final MediaTypeRepository repository;
    private final MediaTypeMapper mapper;

    public MediaTypeServiceImpl(
            MediaTypeRepository repository,
            MediaTypeMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void addObject(MediaTypeRequest request) {
        MediaType mediaType = mapper.requestToObject(request);

        MediaTypeEntity entity = mapper.objectToEntity(mediaType);
        entity.setCreateDate(new Date());
        entity.setId(null);
        repository.save(entity);
    }

    @Override
    public void updateObject(MediaTypeRequest request) {
        MediaType mediaType = getObjectById(request.id());
        if(!mediaType.equals(request)){
            repository.save(mapper.requestToEntityForUpdate(request, mediaType));
        }
    }
}
