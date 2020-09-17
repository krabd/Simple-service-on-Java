package com.krab.rest.services;

import com.krab.rest.entity.Record;
import com.krab.rest.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RecordsService {

    Record addRecord(long authorId, String description) throws ResourceNotFoundException;

    Record updateRecord(long id, long authorId, String description) throws ResourceNotFoundException;

    List<Record> getRecordsByAuthorId(long authorId);

    List<Record> getRecordsByAuthorFirstName(String authorFirstName);
}
