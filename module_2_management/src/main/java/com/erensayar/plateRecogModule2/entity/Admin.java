package com.erensayar.plateRecogModule2.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author erensayar
 */
@Entity
@Table(name = "admin")
@Data
public class Admin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String pass;


}
