package com.krab.rest.mapper;

import com.krab.rest.dto.AbstractDto;
import com.krab.rest.entity.AbstractEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public abstract class AbstractMapper<E extends AbstractEntity, D extends AbstractDto> implements Mapper<E, D> {

    @Autowired
    private ModelMapper mapper;

    private Class<E> entityClass;
    private Class<D> dtoClass;

    AbstractMapper(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public E toEntity(D dto) {
        return Objects.isNull(dto)
                ? null
                : mapper.map(dto, entityClass);
    }

    @Override
    public D toDto(E entity) {
        return Objects.isNull(entity)
                ? null
                : mapper.map(entity, dtoClass);
    }
}