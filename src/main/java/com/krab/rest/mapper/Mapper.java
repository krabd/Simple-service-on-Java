package com.krab.rest.mapper;

import com.krab.rest.dto.AbstractDto;
import com.krab.rest.entity.AbstractEntity;

public interface Mapper<E extends AbstractEntity, D extends AbstractDto> {

    E toEntity(D dto);

    D toDto(E entity);
}