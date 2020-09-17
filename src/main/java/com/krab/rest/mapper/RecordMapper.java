package com.krab.rest.mapper;

import com.krab.rest.dto.RecordDto;
import com.krab.rest.entity.Record;
import com.krab.rest.repositories.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class RecordMapper extends AbstractMapper<Record, RecordDto> {

    private final ModelMapper mapper;
    private final AuthorRepository authorRepository;

    @Autowired
    public RecordMapper(ModelMapper mapper, AuthorRepository authorRepository) {
        super(Record.class, RecordDto.class);
        this.mapper = mapper;
        this.authorRepository = authorRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Record.class, RecordDto.class)
                .addMappings(m -> m.skip(RecordDto::setAuthorId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(RecordDto.class, Record.class)
                .addMappings(m -> m.skip(Record::setAuthor)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(Record source, RecordDto destination) {
        destination.setAuthorId(getId(source));
    }

    private long getId(Record source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getAuthor().getId();
    }

    @Override
    void mapSpecificFields(RecordDto source, Record destination) {
        destination.setAuthor(authorRepository.findById(source.getAuthorId()).orElse(null));
    }
}