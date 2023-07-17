package com.bcbsm.ex4.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    private String username;


    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

}
