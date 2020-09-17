package com.krab.rest.services;

import com.krab.rest.dto.RecordDto;
import com.krab.rest.exceptions.ResourceNotFoundException;

import java.util.List;

public interface RecordsService {

    RecordDto addRecord(long authorId, String description) throws ResourceNotFoundException;

    RecordDto updateRecord(long id, long authorId, String description) throws ResourceNotFoundException;

    List<RecordDto> getRecordsByAuthorId(long authorId);

    List<RecordDto> getRecordsByAuthorFirstName(String authorFirstName);
}
