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
@Table(name = "UserRole")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "role_id")
    private Integer roleId;


}
