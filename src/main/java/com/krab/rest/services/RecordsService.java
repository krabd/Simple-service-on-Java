package com.krab.rest.services;

import com.krab.rest.dto.RecordDto;
import com.krab.rest.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RecordsService {

    RecordDto addRecord(long authorId, String description) throws ResourceNotFoundException;

    RecordDto updateRecord(long id, long authorId, String description) throws ResourceNotFoundException;

    List<RecordDto> getRecordsByAuthorId(long authorId);

    List<RecordDto> getRecordsByAuthorFirstName(String authorFirstName);
}
