package com.krab.rest.services;

import com.krab.rest.dto.RecordDto;
import com.krab.rest.entity.Author;
import com.krab.rest.entity.Record;
import com.krab.rest.exceptions.ResourceNotFoundException;
import com.krab.rest.mapper.RecordMapper;
import com.krab.rest.repositories.AuthorRepository;
import com.krab.rest.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordsServiceImpl implements RecordsService {

    private final RecordRepository recordRepository;
    private final AuthorRepository authorRepository;
    private final RecordMapper recordMapper;

    @Autowired
    public RecordsServiceImpl(RecordRepository recordRepository, AuthorRepository authorRepository, RecordMapper recordMapper) {
        this.recordRepository = recordRepository;
        this.authorRepository = authorRepository;
        this.recordMapper = recordMapper;
    }

    @Override
    public RecordDto addRecord(long authorId, String description) throws ResourceNotFoundException {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author not found for this id :: " + authorId));
        Record record = new Record(description);
        record.setAuthor(author);
        final Record addedRecord = recordRepository.save(record);
        return recordMapper.toDto(addedRecord);
    }

    @Override
    public RecordDto updateRecord(long id, long authorId, String description) throws ResourceNotFoundException {
        Record updatingRecord = recordRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Record not found for this id :: " + id));
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author not found for this id :: " + authorId));
        updatingRecord.setAuthor(author);
        updatingRecord.setDescription(description);
        final Record updatedRecord = recordRepository.save(updatingRecord);
        return recordMapper.toDto(updatedRecord);
    }

    @Override
    public List<RecordDto> getRecordsByAuthorId(long authorId) {
        return recordRepository.findByAuthorId(authorId)
                .stream()
                .map(recordMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecordDto> getRecordsByAuthorFirstName(String authorFirstName) {
        return recordRepository.getByAuthorFirstName(authorFirstName)
                .stream()
                .map(recordMapper::toDto)
                .collect(Collectors.toList());
    }
}
