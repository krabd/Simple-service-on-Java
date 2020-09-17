package com.krab.rest.services;

import com.krab.rest.entity.Author;
import com.krab.rest.entity.Record;
import com.krab.rest.exceptions.ResourceNotFoundException;
import com.krab.rest.repositories.AuthorRepository;
import com.krab.rest.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecordsServiceImpl implements RecordsService {

    private RecordRepository recordRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public RecordsServiceImpl(RecordRepository recordRepository, AuthorRepository authorRepository) {
        this.recordRepository = recordRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Record addRecord(long authorId, String description) throws ResourceNotFoundException {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author not found for this id :: " + authorId));
        Record record = new Record(description);
        record.setAuthor(author);
        final Record addedRecord = recordRepository.save(record);
        return addedRecord;
    }

    @Override
    public Record updateRecord(long id, long authorId, String description) throws ResourceNotFoundException {
        Record updatingRecord = recordRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Record not found for this id :: " + id));
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author not found for this id :: " + authorId));
        updatingRecord.setAuthor(author);
        updatingRecord.setDescription(description);
        final Record updatedRecord = recordRepository.save(updatingRecord);
        return updatedRecord;
    }

    @Override
    public List<Record> getRecordsByAuthorId(long authorId) {
        return recordRepository.findByAuthorId(authorId);
    }

    @Override
    public List<Record> getRecordsByAuthorFirstName(String authorFirstName) {
        return recordRepository.getByAuthorFirstName(authorFirstName);
    }
}
