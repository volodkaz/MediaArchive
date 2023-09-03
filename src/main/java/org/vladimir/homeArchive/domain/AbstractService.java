package org.vladimir.homeArchive.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.vladimir.homeArchive.exeption.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractService <T, E, R, Q> implements ServiceIntf<R, Q>{
    protected final CrudRepository<E, Long> repository;
    protected final MapperIntf<T, E, R, Q> mapper;

    protected T getObjectById(Long id){
        final E entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Не найдено медия с id " + id));
        return mapper.entityToObject(entity);
    }

    protected List<T> getAllObjects() {
        final Iterable<E> entities = repository.findAll();

        final List<T> mediaData = new ArrayList<>();
        for (E entity : entities) {
            mediaData.add(mapper.entityToObject(entity));
        }
        return mediaData;
    }
    @Override
    public R getObjectResponseById(Long id) {
        final T property = getObjectById(id);
        return mapper.objectToResponse(property);
    }
    @Override
    public List<R> getAllObjectsResponse() {
        return mapper.objectsToResponse(getAllObjects());
    }
    @Override
    public void deleteObject(Long id) {
        repository.deleteById(id);
    }
}
