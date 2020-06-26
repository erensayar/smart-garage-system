package com.erensayar.plateRecogModule2.service;

import com.erensayar.plateRecogModule2.entity.AuthorizedPlate;
import com.erensayar.plateRecogModule2.repository.AuthorizedPlatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author erensayar
 */
@Service
public class AuthorizedPlateService implements IAuthorizedPlateService {

    //--------------------Dependency Injection----------------------
    AuthorizedPlatesRepository authorizedPlatesRepository;

    @Autowired
    public AuthorizedPlateService(AuthorizedPlatesRepository authorizedPlatesRepository) {
        this.authorizedPlatesRepository = authorizedPlatesRepository;
    }

    //---------------------Basic DB Process-------------------------
    @Override
    public List<AuthorizedPlate> getAll() {
        return authorizedPlatesRepository.findAll();
    }

    @Override
    public Optional<AuthorizedPlate> getAuthorityPlateById(Integer id) {
        return authorizedPlatesRepository.findById(id);
    }

    @Override
    public void addAuthorityPlate(AuthorizedPlate authorizedPlate) {
        authorizedPlatesRepository.save(authorizedPlate);
    }

    @Override
    public void deleteById(Integer id) {
        authorizedPlatesRepository.deleteById(id);
    }

    @Override
    public AuthorizedPlate findByPlate(String plate) {
        return authorizedPlatesRepository.findByPlate(plate);
    }


}
