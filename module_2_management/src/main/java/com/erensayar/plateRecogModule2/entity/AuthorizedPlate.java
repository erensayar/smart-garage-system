package com.erensayar.plateRecogModule2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author erensayar
 */
@Entity
@Table(name = "authority_plate")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizedPlate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String plate;


}
