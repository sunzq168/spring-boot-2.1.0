package com.sun.zq.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author sunzheng
 */
@Data
@ToString
@Entity
@Table(name = "User")
public class User implements Serializable {
    private static final long serialVersionUID = 3777394554721084437L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String loginName;
    private String password;
    private String name;
    private String email;
    private Integer age;

}
