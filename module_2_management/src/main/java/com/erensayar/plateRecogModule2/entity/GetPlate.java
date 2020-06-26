package com.erensayar.plateRecogModule2.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author erensayar
 */
//This Class Just Take New Authorized Plate From Frontend Form
@Data
public class GetPlate {
    private String plate;
    private Date date;
}
