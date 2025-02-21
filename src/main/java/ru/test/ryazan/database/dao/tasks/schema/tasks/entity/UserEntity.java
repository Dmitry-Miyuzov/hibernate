package ru.test.ryazan.database.dao.tasks.schema.tasks.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users", schema = "test1")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
