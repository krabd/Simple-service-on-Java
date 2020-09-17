package com.krab.rest.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "records")
@EqualsAndHashCode(callSuper = false)
@Setter
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
