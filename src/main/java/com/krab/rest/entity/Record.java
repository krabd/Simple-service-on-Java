package com.krab.rest.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "records")
@Setter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Record extends AbstractEntity {

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;

    public Record(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Author getAuthor() {
        return author;
    }

}
