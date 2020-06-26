package com.erensayar.plateRecogModule2.service;


import com.erensayar.plateRecogModule2.entity.Plate;
import com.erensayar.plateRecogModule2.repository.PlatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author erensayar
 */
@Service
public class PlateService implements IPlateService {

    //--------------------Dependency Injection---------------
    PlatesRepository platesRepository;

    @Autowired
    public PlateService(PlatesRepository platesRepository) {
        this.platesRepository = platesRepository;
    }

    //---------------------Basic DB Process-------------------------
    @Override
    public List<Plate> getAll() {
        return platesRepository.findAll();
    }

    @Override
    public Optional<Plate> getPlateById(Integer id) {
        return platesRepository.findById(id);
    }

    @Override
    public List<Plate> findByPlateContaining(String plate) {
        return platesRepository.findByPlateContaining(plate);
    }

    @Override
    public Long countByPlate(String plate) {
        return platesRepository.countByPlate(plate);
    }

    @Override
    public void addPlate(Plate plate) {
        platesRepository.save(plate);
    }

    @Override
    public void deleteById(Integer id) {
        platesRepository.deleteById(id);
    }

    //-----------------Plate Service Process--------------------


}
