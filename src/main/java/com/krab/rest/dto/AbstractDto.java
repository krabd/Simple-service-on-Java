package com.krab.rest.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AbstractDto implements Serializable {

    private long id;
}
