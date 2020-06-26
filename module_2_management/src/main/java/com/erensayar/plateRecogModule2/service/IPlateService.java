package com.erensayar.plateRecogModule2.service;


import com.erensayar.plateRecogModule2.entity.Plate;

import java.util.List;
import java.util.Optional;

/**
 * @author erensayar
 */
public interface IPlateService {

    List<Plate> getAll();

    Optional<Plate> getPlateById(Integer id);

    List<Plate> findByPlateContaining(String plate);

    Long countByPlate(String plate);

    void addPlate(Plate plate);

    void deleteById(Integer id);

}
