package ru.test.ryazan.dao.tasks.schema.dictionaries.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users", schema = "test1")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
}
