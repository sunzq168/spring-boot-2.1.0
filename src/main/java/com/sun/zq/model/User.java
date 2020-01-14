package com.sun.zq.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@ToString
@Entity
@Table(name = "User")
public class User {
    @Id
    private Integer id;
    private String name;
    private String email;
    private Integer age;
}
