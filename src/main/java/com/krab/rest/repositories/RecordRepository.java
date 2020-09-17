package com.krab.rest.repositories;

import com.krab.rest.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findByAuthorId(long authorId);

    @Query("select r from Record r where r.author.firstName = :firstName")
    List<Record> getByAuthorFirstName(@Param("firstName") String firstName);
}
