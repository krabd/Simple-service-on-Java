package com.krab.rest.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
