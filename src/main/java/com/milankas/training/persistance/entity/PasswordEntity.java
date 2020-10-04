package com.milankas.training.persistance.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "passwords")
public class PasswordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique=true)
    private UUID id;

    @Column(name="hash")
    private String hash;

    @Column(name="salt")
    private String salt;

    @Column(name="status")
    private Integer status;

}
