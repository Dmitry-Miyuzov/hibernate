package ru.test.ryazan.dao.tasks.schema.tasks.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dictionaries", schema = "test2")
public class DictionaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
