package com.sun.zq.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author sunzheng
 */
@Data
@ToString
@Entity
@Table(name = "Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
