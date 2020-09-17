package com.krab.rest.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecordDto extends AbstractDto {

    private String description;
    private long authorId;
}
