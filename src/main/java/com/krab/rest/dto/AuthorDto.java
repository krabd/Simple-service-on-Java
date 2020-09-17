package com.krab.rest.dto;

import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthorDto extends AbstractDto {

    private String firstName;
    private String lastName;
    List<RecordDto> records;
}
