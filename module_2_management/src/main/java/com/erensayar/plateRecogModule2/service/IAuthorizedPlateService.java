package com.erensayar.plateRecogModule2.service;


import com.erensayar.plateRecogModule2.entity.AuthorizedPlate;
import com.erensayar.plateRecogModule2.entity.Plate;

import java.util.List;
import java.util.Optional;

/**
 * @author erensayar
 */
public interface IAuthorizedPlateService {

    List<AuthorizedPlate> getAll();

    Optional<AuthorizedPlate> getAuthorityPlateById(Integer id);

    void addAuthorityPlate(AuthorizedPlate authorizedPlate);

    void deleteById(Integer id);

    AuthorizedPlate findByPlate(String plate);
}
