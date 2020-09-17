package com.krab.rest.entity;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private long id;

    public Long getId() {
        return id;
    }
}
