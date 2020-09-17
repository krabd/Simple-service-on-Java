package com.krab.rest.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors")
@EqualsAndHashCode(callSuper = false)
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author extends AbstractEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author", cascade = CascadeType.ALL)
    List<Record> records;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Record> getRecords() {
        return records;
    }
}
