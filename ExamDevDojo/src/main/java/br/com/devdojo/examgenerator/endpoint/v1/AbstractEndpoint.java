package br.com.devdojo.examgenerator.endpoint.v1;

import br.com.devdojo.examgenerator.exception.ResourceNotFoundException;
import br.com.devdojo.examgenerator.persistence.model.AbstractEntity;
import br.com.devdojo.examgenerator.persistence.repository.CustomPagingAndSortRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.reflect.ParameterizedType;

public class AbstractEndpoint<T extends AbstractEntity, R extends CustomPagingAndSortRepository<T, Long>> {

    private Class<T> mEntity;
    private final R mRepository;

    protected AbstractEndpoint(R repository) {
        mRepository = repository;
        mEntity = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected ResponseEntity<?> getEntityById(@PathVariable long id) {
        return returnObjectOrNotFount(mRepository.findOne(id));
    }

    protected ResponseEntity<?> listAll() {
        return new ResponseEntity<>(returnListObjectOrNotFount(mRepository.findAll()), HttpStatus.OK);
    }

    protected ResponseEntity<?> delete(@PathVariable long id) {
        throwResourceNotFoundIfCoursesDoesNotExist(id);
        mRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    protected ResponseEntity<?> update(T entity) {
        throwResourceNotFoundIfCoursesDoesNotExist(entity);
        mRepository.save(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    protected ResponseEntity<?> create(T entity) {
        return new ResponseEntity<>(mRepository.save(entity), HttpStatus.OK);
    }

    protected void throwResourceNotFoundIfCoursesDoesNotExist(T entity) {
        if (entity == null || entity.getId() == null ||
                mRepository.findOne(entity.getId()) == null)
            throw new ResourceNotFoundException(mEntity.getSimpleName() + " not found");
    }

    protected void throwResourceNotFoundIfCoursesDoesNotExist(long id) {
        if (id == 0 || mRepository.findOne(id) == null)
            throw new ResourceNotFoundException(mEntity.getSimpleName() + " not found");
    }

    protected ResponseEntity<?> returnObjectOrNotFount(Object object) {
        if (object == null) throw new ResourceNotFoundException("Not found");

        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    protected ResponseEntity<?> returnListObjectOrNotFount(Iterable<?> list) {
        if (list == null || !list.iterator().hasNext())
            throw new ResourceNotFoundException("Not found");

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
